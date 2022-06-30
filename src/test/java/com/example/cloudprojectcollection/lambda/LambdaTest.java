package com.example.cloudprojectcollection.lambda;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Wwx
 * @createTime: 2022年06月30日
 * @version: 0.0.1
 * @Description: lambda测试
 */

public class LambdaTest {

    @Test
    public void filterTest(){
        List<Integer> demoList = new ArrayList<>();
        demoList.add(10);
        demoList.add(7);
        demoList.add(15);
        // 筛选集合中元素大于7的元素，返回新的集合
        List<Integer> collect = demoList.stream().filter(item -> item > 7).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void sortedTest(){
        List<Integer> demoList = new ArrayList<>();
        demoList.add(10);
        demoList.add(7);
        demoList.add(15);
        // 筛选集合中元素大于7的元素，返回新的集合
        List<Integer> collect = demoList.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void allMatchTest(){
        List<Integer> demoList = new ArrayList<>();
        demoList.add(10);
        demoList.add(7);
        demoList.add(15);
        // 筛选集合中元素大于7的元素，返回新的集合
        boolean collect = demoList.stream().allMatch(item -> item>7);
        System.out.println(collect);
        // 只要集合中一个元素值大于12，就返回true
        boolean b2 = demoList.stream().anyMatch(item -> item > 12);
        System.out.println(b2);
    }

    //收集集合中所有元素的某个属性值。
    @Test
    public void mapTest(){
        List<Account> demoList = new ArrayList<>();
        Account account = new Account();
        account.setId("11");
        demoList.add(account);
        Account account1 = new Account();
        account1.setId("12");
        demoList.add(account1);
        System.out.println(demoList);
        List<String> collect = demoList.stream().map(Account::getId).collect(Collectors.toList());
        System.out.println(collect);
        String collect1 = demoList.stream().map(Account::getId).collect(Collectors.joining(","));
        System.out.println(collect1);
    }

    @Test
    public void collectTest(){
        List<Account> demoList = new ArrayList<>();
        Account account = new Account();
        account.setId("11");
        account.setName("xww");
        demoList.add(account);

        Account account1 = new Account();
        account1.setId("12");
        account1.setName("wwx");
        demoList.add(account1);

        Account account2 = new Account();
        account2.setId("11");
        account2.setName("xww1");
        demoList.add(account2);
        // 集合转map，key为id，value为元素本身。
        // 有重复value值取k1（基本上key为主键value本身不会重复）
        Map<String, Account> map = demoList.stream().collect(Collectors.toMap(Account::getId, v -> v, (k1, k2) -> k1));
        System.out.println(map);

        // 根据元素中的属性id对流分组，重复的放在同一个集合里
        Map<String, List<Account>> map1 = demoList.stream().collect(Collectors.groupingBy(Account::getId));
        System.out.println(map1);
    }

    //用于跳过元素
    @Test
    public void skipTest(){
        List<Integer> demoList = new ArrayList<>();
        demoList.add(10);
        demoList.add(7);
        demoList.add(15);
        List<Integer> collect = demoList.stream().skip(1).limit(3).collect(Collectors.toList());
        System.out.println(collect);
    }
}

@Data
class Account{
    private String id;
    private String name;
}
