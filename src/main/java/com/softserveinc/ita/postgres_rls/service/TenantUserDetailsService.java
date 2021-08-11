package com.softserveinc.ita.postgres_rls.service;

import com.softserveinc.ita.postgres_rls.entity.TenantUserWrapper;
import com.softserveinc.ita.postgres_rls.entity.User;
import com.softserveinc.ita.postgres_rls.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TenantUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("user not found"));

        return new TenantUserWrapper(user);
    }
}
