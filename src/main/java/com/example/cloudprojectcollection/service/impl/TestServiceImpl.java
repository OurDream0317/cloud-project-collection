package com.example.cloudprojectcollection.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cloudprojectcollection.entity.dto.City;
import com.example.cloudprojectcollection.mapper.db2.Db2Mapper;
import com.example.cloudprojectcollection.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Wwx
 * @createTime: 2022年07月11日
 * @version: 0.0.1
 * @Description:
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    Db2Mapper db2Mapper;

    @DS("db2")
    @Override
    public List queryList(){
        System.out.println("多数据元配置！！！！！！！！！！！！！！！！！！");
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        List<City> list = db2Mapper.selectList(queryWrapper);
        list.forEach(e -> System.out.println(e));
        return list;
    }
}
