package moneyger4u;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import java.util.concurrent.TimeUnit;

@Configuration
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

    @Bean
    DataSource realDataSource() {
        DataSourceBuilder factory = DataSourceBuilder
                .create(this.properties.getClassLoader())
                .url(this.properties.getUrl())
                .username(this.properties.getUsername())
                .password(this.properties.getPassword());
        this.dataSource = factory.build();
        setValidationQuery(this.dataSource);
        return this.dataSource;
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

    @Bean
    DataSource dataSource() {
        return new Log4jdbcProxyDataSource(this.dataSource);
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
