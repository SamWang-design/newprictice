package com.wxy.newprictice.controller;


import com.wxy.newprictice.entity.User;
import com.wxy.newprictice.service.UserService;
import com.wxy.newprictice.util.DateUtil;

import com.wxy.newprictice.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "查找所有", description = "查询的维护")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;



    @ApiOperation("查询表的全部")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<User> findall() {
        List<User> all = userService.findall();
        return all;
    }

    @ApiOperation("增加一条")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(User user) {
        userService.addOrUpdate(user);
    }


//
//    @ApiOperation("获取短信验证码")
//    @RequestMapping(value = "/getMassage", method = RequestMethod.POST)
//    public void findall(String phone) {
//      sendMassage send = new sendMassage().send(phone,"928312");
//
//    }

    @ApiOperation("查一个")
    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    public User findone(String username) {
        User findbyusername = userService.findbyusername(username);
        return findbyusername;
    }


    @ApiOperation("检查shiro权限控制")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("rememberme") Boolean rememberme,
                        @RequestParam("vcode") String vcode) throws Exception {

        if(vcode==null||vcode==""){
            throw new Exception("验证码不能为空");
        }
        Session session = SecurityUtils.getSubject().getSession();
        //转化成小写字母
        vcode = vcode.toLowerCase();
        String v = (String) session.getAttribute("_code");
        if(!v.equals(vcode)){
            throw new Exception("验证码错误");
        }
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememberme);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            User user = new User();
            user.setLast_login_time(DateUtil.getCurrent24DateTotal());

            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }

    @ApiOperation("检查shiro权限控制")
    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            User user = new User();
            user.setLast_login_time(DateUtil.getCurrent24DateTotal());

            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }


}
