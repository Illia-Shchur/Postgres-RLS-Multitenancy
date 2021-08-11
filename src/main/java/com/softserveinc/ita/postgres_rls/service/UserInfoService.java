package com.softserveinc.ita.postgres_rls.service;

import com.softserveinc.ita.postgres_rls.entity.UserInfo;
import com.softserveinc.ita.postgres_rls.repo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public List<UserInfo> findAll(){
        return userInfoRepository.findAll();
    }

    public UserInfo findById(Long id){
        return userInfoRepository.findById(id).orElse(null);
    }

    public UserInfo findByText(String text){
        return userInfoRepository.findByText(text);

    }

    @Transactional
    public void deleteById(Long id){
         userInfoRepository.deleteById(id);
    }

    @Transactional
    public List<UserInfo> findOne(Long id){
        Specification<UserInfo> specification =
                (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("id"),4);
        return userInfoRepository.findAll(specification, Sort.by("text"));
    }

}
