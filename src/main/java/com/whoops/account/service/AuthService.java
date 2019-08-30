package com.whoops.account.service;

import com.whoops.account.pojo.Auth;
import com.whoops.account.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    public Auth getAuthById(Long id){
        return authRepository.getOne(id);
    }
}
