package com.wxy.newprictice.dao;

import com.wxy.newprictice.entity.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface UserMapper extends Mapper<User> {

}
