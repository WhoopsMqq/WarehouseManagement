package com.whoops.account.controller;

import com.whoops.account.pojo.Auth;
import com.whoops.account.pojo.User;
import com.whoops.account.service.AuthService;
import com.whoops.account.service.UserService;
import com.whoops.vo.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login(){
        return "/page/login/login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        model.addAttribute("errorMsg","用户名或密码错误");
        return "/page/login/login";
    }

    @RequestMapping("/userList")
    public String userList(Model model){
        return "/page/user/userList";
    }

    @GetMapping("/userListJson")
    @ResponseBody
    public TableData userListJson(){
        List<User> userList = userService.loadAllUser();
        return new TableData(0,"",userList.size(),userList);
    }

    @GetMapping("/userAdd")
    public String userAdd(){
        return "/page/user/userAdd";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestParam("username") String username,@RequestParam("name") String name){
        Auth auth = authService.getAuthById(1L);
        List<Auth> authList = new ArrayList<>();
        authList.add(auth);
        User user = new User(username,name,"123456",authList);
        String msg = userService.saveUser(user);
        return msg;
    }
}
