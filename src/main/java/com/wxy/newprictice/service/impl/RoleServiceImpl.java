package com.wxy.newprictice.service.impl;

import com.wxy.newprictice.dao.RoleMapper;
import com.wxy.newprictice.entity.Role;
import com.wxy.newprictice.entity.User;
import com.wxy.newprictice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> findall() {
        List<Role> roles = roleMapper.selectAll();
        return roles;
    }

    @Override
    public Role findbyUid(Integer uid) {
        Condition condition = new Condition(Role.class);
        Example.Criteria c = condition.createCriteria();
      if(uid!=null){
          c.andEqualTo("uid",uid);
      }
        Role role = roleMapper.selectOneByExample(condition);
      return role;
    }



    @Override
    public void add(Role role) {

    }
}
