package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("User")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityManager securityManager;

    @RequestMapping("loginPage")
    public String loginPage(){
        return "login";
    }
    @RequestMapping("login")
    public String login(User user){
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try{
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()){
                return "redirect:index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:loginPage";
    }
    @RequestMapping("index")
    public String index(@RequestParam(defaultValue = "1")int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<User> userList = userService.getUserList();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }
    @RequestMapping("addUser")
    public String addUser(){
        return "add";
    }
    @RequestMapping("saveUser")
    public String saveUser(User user){
        int i = userService.addUser(user);
        if (i>0){
            return "redirect:index";
        }
        return "addUser";
    }
    @RequestMapping("deleteUser")
    @ResponseBody
    public String deleteUser(int uid){
        int i = userService.deleteUser(uid);
        if (i>0){
            return "success";
        }
        return "false";
    }
    @RequestMapping("editUser")
    public String editUser(int uid,Model model){
        User user = userService.getUserByUid(uid);
        model.addAttribute("user",user);
        return "update";
    }























    @RequestMapping("updateUser")
    public String updateUser(User user){
        int i = userService.updateUser(user);
        if(i>0){
            return "redirect:index";
        }
        return "redirect:editUser?uid="+user.getUid();
    }
    @RequestMapping("unauth")
    public String unauth(){
        return "unauth";
    }



}
