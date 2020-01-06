package com.wxy.newprictice.service.impl;

import com.wxy.newprictice.dao.PermmisionMapper;
import com.wxy.newprictice.entity.Permmission;
import com.wxy.newprictice.entity.Role;
import com.wxy.newprictice.service.PermmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class PermmissionServiceImpl implements PermmissionService {
    @Autowired
    PermmisionMapper permmisionMapper;
    @Override
    public List<Permmission> findall() {
        List<Permmission> permmissions = permmisionMapper.selectAll();
        return permmissions;
    }

    @Override
    public Permmission findbyRid(Integer rid) {
        Condition condition = new Condition(Permmission.class);
        Example.Criteria c = condition.createCriteria();
        if(rid!=null){
            c.andEqualTo("rid",rid);
        }
        Permmission permmission = permmisionMapper.selectOneByExample(condition);
        return permmission;
    }

    @Override
    public void add(Permmission permmission) {

    }

}
