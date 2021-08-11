package com.softserveinc.ita.postgres_rls.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSourceResolver extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getPostgresUser() == null
                ? PostgresUser.APP_USER : TenantContext.getPostgresUser();
    }
}
