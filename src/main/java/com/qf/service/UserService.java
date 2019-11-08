package com.qf.service;

import com.qf.pojo.User;

import java.util.List;

public interface UserService {
    //    通过uid获取User
    public User getUserByUid(int uid);
    //通过通过Id获取密码
    public String getPasswordByUsername(String username);
    //通过通过uid获取权限
    public String getRoleByUsername(String username);
    //添加用户
    public int addUser(User user);
    //    删除用户
    public int deleteUser(int uid);
    //更新用户
    public int updateUser(User user);
    //    得到User表
    public List<User> getUserList();

}
