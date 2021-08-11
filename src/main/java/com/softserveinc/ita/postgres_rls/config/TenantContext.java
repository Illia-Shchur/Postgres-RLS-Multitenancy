package com.softserveinc.ita.postgres_rls.config;

import java.util.List;

public class TenantContext {
    private static ThreadLocal<PostgresUser> postgresUser = new InheritableThreadLocal<>();
    private static ThreadLocal<List<Long>> currentTenants = new InheritableThreadLocal<>();

    private TenantContext(){
        postgresUser.set(PostgresUser.JOB_USER);
    }

    public static PostgresUser getPostgresUser() {
        return postgresUser.get();
    }

    public static void setPostgresUser(PostgresUser user) {
        postgresUser.set(user);
    }

    public static void clearUser() {
        postgresUser.set(null);
    }

    public static List<Long> getCurrentTenants() {
        return currentTenants.get();
    }

    public static void setCurrentTenants(List<Long> tenant) {
        currentTenants.set(tenant);
    }

    public static void clearTenants() {
        currentTenants.set(null);
    }
}