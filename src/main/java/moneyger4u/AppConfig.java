package moneyger4u;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.terasoluna.gfw.web.logging.HttpSessionEventLoggingListener;
import org.terasoluna.gfw.web.logging.TraceLoggingInterceptor;
import org.terasoluna.gfw.web.logging.mdc.MDCClearFilter;
import org.terasoluna.gfw.web.logging.mdc.XTrackMDCPutFilter;

import javax.inject.Inject;
import javax.sql.DataSource;

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


    @ConfigurationProperties(prefix = DataSourceAutoConfiguration.CONFIGURATION_PREFIX)
    @Bean
    DataSource realDataSource() {
        DataSourceBuilder factory = DataSourceBuilder
                .create(this.properties.getClassLoader())
                .url(this.properties.getUrl())
                .username(this.properties.getUsername())
                .password(this.properties.getPassword());
        this.dataSource = factory.build();
        return this.dataSource;
    }

    @Bean
    DataSource dataSource() {
        return new Log4jdbcProxyDataSource(this.dataSource);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return filter;
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
