package moneyger4u;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.terasoluna.gfw.web.logging.HttpSessionEventLoggingListener;
import org.terasoluna.gfw.web.logging.TraceLoggingInterceptor;
import org.terasoluna.gfw.web.logging.mdc.MDCClearFilter;
import org.terasoluna.gfw.web.logging.mdc.XTrackMDCPutFilter;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class AppConfig {
    @Inject
    DataSourceProperties properties;
    DataSource dataSource;

    @Bean
    DozerBeanMapperFactoryBean beanMapper() throws Exception {
        DozerBeanMapperFactoryBean factoryBean = new DozerBeanMapperFactoryBean();
        factoryBean.setMappingFiles(new Resource[]{
                new ClassPathResource("META-INF/dozer/dozer-mapping.xml")
        });
        return factoryBean;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder(512);
    }

    static void setValidationQuery(DataSource dataSource) {
        if (dataSource instanceof org.apache.tomcat.jdbc.pool.DataSource) {
            org.apache.tomcat.jdbc.pool.DataSource ds = (org.apache.tomcat.jdbc.pool.DataSource) dataSource;
            ds.setValidationQuery("SELECT 1");
            ds.setTestOnBorrow(true);
            ds.setTestWhileIdle(true);
            ds.setValidationInterval(TimeUnit.MINUTES.toMillis(1));
            ds.setTimeBetweenEvictionRunsMillis((int) TimeUnit.MINUTES.toMillis(5));
        }
    }

    @Configuration
    @Profile("db.property")
    public static class PropertyDbConfiguration {
        @Inject
        DataSourceProperties dataSourceProperties;

        @Bean
        DataSourceBuilder realDataSourceBuilder() throws URISyntaxException {
            String url = this.dataSourceProperties.getUrl();
            String username = this.dataSourceProperties.getUsername();
            String password = this.dataSourceProperties.getPassword();

            DataSourceBuilder factory = DataSourceBuilder
                    .create(this.dataSourceProperties.getClassLoader())
                    .url(url)
                    .username(username)
                    .password(password);
            return factory;
        }

        @Bean
        DataSource dataSource(DataSourceBuilder factory) {
            DataSource dataSource = factory.build();
            setValidationQuery(dataSource);
            return new Log4jdbcProxyDataSource(dataSource);
        }
    }

    @Configuration
    @Profile("db.docker")
    public static class DockerDbConfiguration {
        @Value("#{systemEnvironment['MYSQL_PORT_3306_TCP_ADDR']}")
        String mysqlHost;
        @Value("#{systemEnvironment['MYSQL_PORT_3306_TCP_PORT']}")
        int mysqlPort;
        @Value("${spring.datasource.database:moneyger4u}")
        String database;
        @Inject
        DataSourceProperties dataSourceProperties;

        @Bean
        DataSourceBuilder realDataSourceBuilder() {
            String url = "jdbc:mysql://" + mysqlHost + ":" + mysqlPort + "/" + database + "?zeroDateTimeBehavior=convertToNull";
            String username = this.dataSourceProperties.getUsername();
            String password = this.dataSourceProperties.getPassword();

            DataSourceBuilder factory = DataSourceBuilder
                    .create()
                    .url(url)
                    .username(username)
                    .password(password);
            return factory;
        }

        @Bean
        DataSource dataSource(DataSourceBuilder factory) {
            DataSource dataSource = factory.build();
            setValidationQuery(dataSource);
            checkDbAvailiability(dataSource);
            return new Log4jdbcProxyDataSource(dataSource);
        }

        void checkDbAvailiability(DataSource dataSource) {
            // ** Hack for Docker Compose **
            // Check Availability because DB could not start yet by Docker Compose
            log.info("Check DB availability... ({}:{})", mysqlHost, mysqlPort);

            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            Level currentLevel = context.getLogger("org.apache.tomcat.jdbc.pool.ConnectionPool").getLevel();
            context.getLogger("org.apache.tomcat.jdbc.pool.ConnectionPool").setLevel(Level.OFF);
            int retryMax = 10;
            for (int i = 0; i < retryMax; i++) {
                if (i > 0) {
                    long sleep = 2 * i;
                    log.info("Retry after {} sec ({}/{})", sleep, i + 1, retryMax);
                    try {
                        TimeUnit.SECONDS.sleep(sleep);
                    } catch (InterruptedException e) {
                        log.info("Interrupted!");
                        Thread.currentThread().interrupt();
                    }
                }
                try (Connection connection = dataSource.getConnection();
                     Statement statement = connection.createStatement()) {
                    statement.execute("SELECT 1");
                    log.info("OK");
                    break;
                } catch (SQLException e) {
                    String message = e.getMessage();
                    if (message != null) {
                        message = message.replace("\n", "").replace("\r", "");
                    }
                    log.info("NG ({})", message);
                    if (i + 1 == retryMax) {
                        throw new IllegalStateException("Failed checking DB availability", e);
                    }
                }
            }
            context.getLogger("org.apache.tomcat.jdbc.pool.ConnectionPool").setLevel(currentLevel);
        }
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE - 1)
    MDCClearFilter mdcClearFilter() {
        MDCClearFilter filter = new MDCClearFilter();
        return filter;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE - 2)
    XTrackMDCPutFilter xTrackMDCPutFilter() {
        XTrackMDCPutFilter filter = new XTrackMDCPutFilter();
        return filter;
    }

    @Bean
    HttpSessionEventLoggingListener httpSessionEventLoggingListener() {
        HttpSessionEventLoggingListener listener = new HttpSessionEventLoggingListener();
        return listener;
    }

    @Bean
    TraceLoggingInterceptor traceLoggingInterceptor() {
        TraceLoggingInterceptor interceptor = new TraceLoggingInterceptor();
        return interceptor;
    }
}
