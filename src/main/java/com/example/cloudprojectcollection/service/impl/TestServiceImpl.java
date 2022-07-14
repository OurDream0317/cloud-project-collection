package com.example.cloudprojectcollection.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cloudprojectcollection.entity.dto.City;
import com.example.cloudprojectcollection.entity.dto.SysMenu;
import com.example.cloudprojectcollection.mapper.db1.Db1Mapper;
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

    @Autowired
    Db1Mapper db1Mapper;

    @DS("db2")
    @Override
    public List queryList(){
        System.out.println("多数据元配置！！！！！！！！！！！！！！！！！！");
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        List<City> list = db2Mapper.selectList(queryWrapper);
        list.forEach(e -> System.out.println(e));
        return list;
    }

    @DS("db1")
    @Override
    public List getTree(){
        System.out.println("获取树菜单结构！！！！！！！！！！！！！！！！！！");
       /* QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        List<SysMenu> nodeList = db1Mapper.selectList(queryWrapper);
        nodeList.forEach(e -> System.out.println(e));*/

        // 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();

        nodeList.add(new TreeNode<>("1", "0", "系统管理", 5));
        nodeList.add(new TreeNode<>("11", "1", "用户管理", 222222));
        nodeList.add(new TreeNode<>("111", "11", "用户添加", 0));
        nodeList.add(new TreeNode<>("2", "0", "店铺管理", 1));
        nodeList.add(new TreeNode<>("21", "2", "商品管理", 44));
        nodeList.add(new TreeNode<>("221", "2", "商品管理2", 2));

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
// 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("order");
        treeNodeConfig.setIdKey("rid");
// 最大递归深度
        treeNodeConfig.setDeep(3);

//转换器
        List<Tree<String>> treeNodes = TreeUtil.build(nodeList, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setWeight(treeNode.getWeight());
                    tree.setName(treeNode.getName());
                    // 扩展属性 ...
                  /*  tree.putExtra("extraField", 666);
                    tree.putExtra("other", new Object());*/
                });


        return treeNodes;
    }
}
