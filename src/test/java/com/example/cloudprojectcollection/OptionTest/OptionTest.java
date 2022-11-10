package com.example.cloudprojectcollection.OptionTest;

import com.google.common.base.Strings;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Wwx
 * @createTime: 2022年07月18日
 * @version: 0.0.1
 * @Description:
 */
@SpringBootTest
public class OptionTest {

    @Test
    public void m1() {
        A a = new A();
        A.B b = new A.B();
       // Assert.isNull(b, "用户名不允许为空！");
        Assert.hasLength("111","不允许为空！！！！");
    }
    @Test
    public void m2() {
        Map map = new HashMap();
        map.put("money","0");
        System.out.println(map.get("money").equals("0"));
    }
    @Test
    public void m3() {

        String  str = "0.0";
        BigDecimal n = new BigDecimal(str);

        System.out.println(new BigDecimal("0.00").compareTo(BigDecimal.ZERO) == 0);
    }
    @Test
    public void m5() {
        Map<String,Object> map = null;
        System.out.println(map.get("tradeType"));
    }

    @Test
    public   void desensitizedIdNumber(){
        List<Map> maps = new ArrayList<>();
        Map map = new HashMap();
        map.put("certificateNumber","412702199505243615");
        Map map2 = new HashMap();
        map2.put("certificateNumber","123456789");
        maps.add(map);
        maps.add(map2);
        List<Map> collect = maps.stream().map(e -> {
            Map map1 = new HashMap();
            map1.put("certificateNumber", getStr(e.get("certificateNumber").toString()));
            return map1;
        }).collect(Collectors.toList());

        System.out.println(collect);
    }

    public String getStr(String idNumber){
        if (!Strings.isNullOrEmpty(idNumber)) {
            if (idNumber.length() == 18){
                idNumber = idNumber.replaceAll("(\\w{6})\\w*(\\w{4})", "$1****$2");
            }else{
                idNumber = idNumber.replaceAll("(\\w{4})\\w*(\\w{4})", "$1****$2");
            }
        }
        return idNumber;
    }

    @Test
    public void mmmm(){
        System.out.println("01".contains("0"));
    }

    @Test
    public void m4() {
        System.out.println("----------大乐透机选开始------------");
        TreeSet<Integer> set = new TreeSet();
        TreeSet<Integer> set1 = new TreeSet();
        while (true){
            set.add((int) (1 + Math.random() * (35)));
            if (set.size()==5){
                break;
            }
        }
        while (true){
            set1.add((int) (1 + Math.random() * (12)));
            if (set1.size()==2){
                break;
            }
        }
        System.out.print(set);
        System.out.println(set1);
        System.out.println("----------大乐透机选结束------------");
        System.out.println("-------------祝您好运--------------");
    }
}
