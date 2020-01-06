package com.wxy.newprictice.controller;

import com.wxy.newprictice.entity.Permmission;
import com.wxy.newprictice.entity.Role;
import com.wxy.newprictice.entity.User;
import com.wxy.newprictice.service.PermmissionService;
import com.wxy.newprictice.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "角色权限管理器", description = "角色权限管理器")
@RequestMapping("/roleperm")
public class RoleAndPermController {
    @Autowired
    PermmissionService permmissionService;
    @Autowired
    RoleService roleService;


    @ApiOperation("查询全部角色列表")
    @RequestMapping(value = "/findAllRole", method = RequestMethod.GET)
    public List<Role> findRoleAll() {
        List<Role> all = roleService.findall();
        return all;
    }

    @ApiOperation("查询全部权限列表")
    @RequestMapping(value = "/findAllPerm", method = RequestMethod.GET)
    public List<Permmission> findPermAll() {
        List<Permmission> all = permmissionService.findall();
        return all;
    }

    @ApiOperation("查询单个权限")
    @RequestMapping(value = "/findOnePerm", method = RequestMethod.POST)
    public Permmission findPermOne(@RequestParam("rid") Integer rid) {
        Permmission permmission = permmissionService.findbyRid(rid);
        return permmission;
    }

    @ApiOperation("查询单个角色")
    @RequestMapping(value = "/findOneRole", method = RequestMethod.POST)
    public Role findRoleOne(@RequestParam("uid") Integer uid) {
        Role role = roleService.findbyUid(uid);
        return role;
    }
}
