package com.softserveinc.ita.postgres_rls.repo;

import com.softserveinc.ita.postgres_rls.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long>, JpaSpecificationExecutor<UserInfo> {

    UserInfo findByText(String text);

    @Query(value = "from UserInfo where id = :id")
    Optional<UserInfo> findById(Long id);

    @Modifying
    @Query(value = "DELETE from UserInfo where id = :id")
    void deleteById(Long id);

}
