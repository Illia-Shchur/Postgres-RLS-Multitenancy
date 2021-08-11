package com.softserveinc.ita.postgres_rls.api;

import com.softserveinc.ita.postgres_rls.config.PostgresUser;
import com.softserveinc.ita.postgres_rls.config.TenantContext;
import com.softserveinc.ita.postgres_rls.entity.UserInfo;
import com.softserveinc.ita.postgres_rls.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoApi {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping(path = "/user-info/{id}")
    public UserInfo getUserInfoById(@PathVariable Long id){
        return userInfoService.findById(id);
    }

    @GetMapping(path = "/user-info/spec/{id}")
    public List<UserInfo> getUserInfoByspec(@PathVariable Long id){
        return userInfoService.findOne(id);
    }

    @GetMapping(path = "/user-info/text/{text}")
    public UserInfo getByText(@PathVariable String text){
         return userInfoService.findByText(text);
    }

    @DeleteMapping(path = "/user-info/{id}")
    public void deleteById(@PathVariable Long id){
         userInfoService.deleteById(id);
    }

    @GetMapping(path = "/user-info")
    public List<UserInfo> getAll(){
        return userInfoService.findAll();
    }

    @GetMapping(path = "/user-info-without-tenant")
    public List<UserInfo> getAllWithoutTenant(){
        TenantContext.setPostgresUser(PostgresUser.JOB_USER);
        return userInfoService.findAll();
    }
}
