package com.wxy.newprictice.controller;

import com.wxy.newprictice.entity.User;
import com.wxy.newprictice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "查找所有", description = "查询的维护")
@RestController
@RequestMapping("/user")
public class TestController {
    @Autowired
    UserService userService;


    @ApiOperation("查询表的全部")
    @RequiresPermissions("user:show")
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public List<User> findall() {

        List<User> all = userService.findall();
        return all;
    }
}
