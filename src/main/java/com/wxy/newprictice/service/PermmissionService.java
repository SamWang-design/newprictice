package com.wxy.newprictice.service;

import com.wxy.newprictice.entity.Permmission;

import java.util.List;

public interface PermmissionService {
    List<Permmission> findall();
    Permmission findbyRid(Integer rid);//根据rid查询权限
    void add(Permmission permmission);
}
