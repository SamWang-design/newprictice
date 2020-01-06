package com.wxy.newprictice.service;


import com.wxy.newprictice.entity.User;

import java.util.List;
public interface UserService {
    List<User> findall();
    User findbyusername(String username);//数据库中的用户名不能重复
    void add(User user);
}
