package com.wxy.newprictice.service;

import com.wxy.newprictice.entity.Role;
import com.wxy.newprictice.entity.User;
import io.swagger.models.auth.In;

import java.util.List;

public interface RoleService {
    List<Role> findall();
    Role findbyUid(Integer uid);//根据id查询角色
    void add(Role role);
}
