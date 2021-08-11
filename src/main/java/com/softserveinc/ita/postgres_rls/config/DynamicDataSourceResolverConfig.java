package com.softserveinc.ita.postgres_rls.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceResolverConfig {

    @Primary
    @Bean
    public DynamicDataSourceResolver test(@Qualifier("superuserDataSource") DataSource superuserDataSource,
                                          @Qualifier("tenantDataSource") DataSource defaultDataSource){
        DynamicDataSourceResolver dynamicDataSourceResolver = new DynamicDataSourceResolver();
        Map<Object,Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(PostgresUser.APP_USER,defaultDataSource);
        dataSourceMap.put(PostgresUser.JOB_USER,superuserDataSource);
        dynamicDataSourceResolver.setTargetDataSources(dataSourceMap);
        return dynamicDataSourceResolver;
    }
}
