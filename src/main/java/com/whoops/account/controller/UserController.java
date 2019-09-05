package com.whoops.account.controller;

import com.whoops.account.pojo.Auth;
import com.whoops.account.pojo.User;
import com.whoops.account.service.AuthService;
import com.whoops.account.service.UserService;
import com.whoops.commons.CurrentUser;
import com.whoops.vo.Response;
import com.whoops.vo.TableData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/userAdd")
    public ResponseEntity<Response> addUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @GetMapping("/delUser/{id}")
    @ResponseBody
    public void delUserById(@PathVariable(name = "id")Long id){
        userService.delUserById(id);
    }

    @GetMapping("/changePwd")
    public String changePwd(Model model){
        User user = CurrentUser.getUser();
        if(user == null || StringUtils.isEmpty(user.getUsername())){
            return "/login";
        }
        model.addAttribute("currentUser",user);
        return "/page/user/changePwd";
    }

    @PostMapping("/changePwd")
    public ResponseEntity<Response> savePwd(){

        return null;
    }
}
