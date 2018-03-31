package name.kaushikam.hibernate.infrastructure.hibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"name.kaushikam.hibernate.domain.model",
                               "name.kaushikam.hibernate.infrastructure.hibernate",
                               "name.kaushikam.hibernate.infrastructure.dbunit"})
public class TestPersistenceContext {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
