package com.softserveinc.ita.postgres_rls.config;

import com.softserveinc.ita.postgres_rls.entity.TenantUserWrapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestTenantFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("In preHandle we are Intercepting the Request");
        System.out.println("____________________________________________");
        TenantUserWrapper principal =(TenantUserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TenantContext.setPostgresUser(PostgresUser.APP_USER);
        TenantContext.setCurrentTenants(principal.getTenants());
        System.out.println("RequestURI::" + request.getRequestURI() +" || Tenants  :: " + principal.getTenants());
        System.out.println("____________________________________________");
        filterChain.doFilter(request, response);
    }

}
