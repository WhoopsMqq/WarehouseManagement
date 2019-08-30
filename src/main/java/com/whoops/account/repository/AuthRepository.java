package com.whoops.account.repository;

import com.whoops.account.pojo.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {

}
