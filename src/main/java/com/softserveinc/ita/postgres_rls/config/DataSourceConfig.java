package com.softserveinc.ita.postgres_rls.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring")
@PropertySource("application.properties")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.multitenancy.tenant.datasource")
    public DataSourceProperties tenantDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("multitenancy.tenant.datasource.hikari")
    public DataSource tenantDataSource() {
        HikariDataSource dataSource = tenantDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        dataSource.setPoolName("tenantDataSource");
        return new TenantAwareDataSource(dataSource);
    }

    @Bean
    public DataSourceProperties superuserDataSourceProperties() {
        DataSourceProperties properties = new DataSourceProperties();
        properties.setUrl("jdbc:postgresql://localhost:5433/tenants_RLS");
        properties.setUsername("job_app_user");
        properties.setPassword("password");
        properties.setDriverClassName("org.postgresql.Driver");
        return properties;
    }

    @Bean
    public DataSource superuserDataSource() {
        HikariDataSource dataSource = superuserDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        dataSource.setPoolName("superuserDataSource");
        return dataSource;
    }


}
