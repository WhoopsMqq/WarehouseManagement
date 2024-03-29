package com.whoops.account.service;

import com.whoops.account.pojo.Auth;
import com.whoops.account.pojo.User;
import com.whoops.account.repository.AuthRepository;
import com.whoops.account.repository.UserRepository;
import com.whoops.commons.CurrentUser;
import com.whoops.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        return user;
    }

    public List<User> loadAllUser(){
        return userRepository.findAll();
    }

    public Response saveUser(User user){
        User existedUser = userRepository.findUserByUsername(user.getUsername());
        if(existedUser != null){
            return new Response(false,"用户名已存在,请换一个!");
        }else if(user.getId() != null && user.getId() != 0L){
            User oldUser = userRepository.getOne(user.getId());
            oldUser.setName(user.getName());
            oldUser.setUsername(user.getUsername());
            User editedUser = userRepository.save(oldUser);
            if(editedUser != null){
                return new Response(true,"修改成功!");
            }
            return  new Response(false,"修改失败!");
        }else{
            user.setPassword(passwordEncoder.encode("123456"));
            Auth auth = authRepository.getOne(1L);
            List<Auth> authList = new ArrayList<>();
            authList.add(auth);
            user.setAuthList(authList);
            User savedUser = userRepository.save(user);
            if(savedUser != null){
                return new Response(true,"保存成功!");
            }
            return new Response(false,"保存失败!");
        }
    }

    public void delUserById(Long id){
        User user = userRepository.getOne(id);
        userRepository.delete(user);
    }

    public Response editPwd(String password){
        try{
            User user = CurrentUser.getUser();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }catch (Exception e){
            return new Response(false,"密码修改失败!");
        }
        return new Response(true,"密码修改成功!");
    }

}
