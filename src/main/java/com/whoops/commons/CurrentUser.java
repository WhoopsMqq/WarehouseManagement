package com.whoops.commons;

import com.whoops.account.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {
    public static User getUser(){
        //判断是否本人访问
        if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }else{
            return null;
        }
    }
}
