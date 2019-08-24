package com.vapasians.shopoholics.repository;

import com.vapasians.shopoholics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("from User where loginName=?1 and loginPwd=?2")
    Optional<User> findUserByLoginNameAndLoginPwd(String username, String password);
}