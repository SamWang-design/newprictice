package com.wxy.newprictice.service.impl;

import com.wxy.newprictice.dao.UserMapper;

import com.wxy.newprictice.entity.User;
import com.wxy.newprictice.service.UserService;

import com.wxy.newprictice.util.RedisUtil;
import com.wxy.newprictice.util.SnowFlakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
public class UserServviceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<User> findall() {
        List<User> findall = userMapper.selectAll();
        //redisUtil.set("emp",findall);
        return findall;
    }

    @Override
    public User findbyusername(String username) {
        Condition condition = new Condition(User.class);
        Example.Criteria c = condition.createCriteria();
        if(StringUtil.isNotEmpty(username)){
            c.andEqualTo("username",username);
        }
        User user = userMapper.selectOneByExample(condition);
        return user;
    }

    @Override
    public void add(User user) {
        int insert = userMapper.insert(user);
    }

}

