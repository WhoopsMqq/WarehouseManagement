package com.whoops.account.service;

import com.whoops.account.pojo.User;
import com.whoops.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        return user;
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public List<User> loadAllUser(){
        return userRepository.findAll();
    }

    @Transactional
    public String saveUser(User user){
//        try {
            userRepository.save(user);
//        }catch (Exception e){
//            return "用户保存失败!";
//        }
        return "用户保存成功!";
    }

    @Transactional
    public String delUserById(Long id){
//        try {
            User user = userRepository.getOne(id);
            userRepository.delete(user);
//        }catch (Exception e){
//            return "删除用户失败!";
//        }
        return "删除用户成功!";
    }

    @Transactional
    public String delUserByIds(List<Long> ids){
//        try {
            for (Long id:ids){
                userRepository.deleteById(id);
            }
//        }catch (Exception e){
//            return "删除用户失败!";
//        }
        return "删除用户成功!";
    }

}
