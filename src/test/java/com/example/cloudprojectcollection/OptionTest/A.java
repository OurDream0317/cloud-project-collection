package com.example.cloudprojectcollection.OptionTest;

import lombok.Data;

/**
 * @Author: Wwx
 * @createTime: 2022年07月18日
 * @version: 0.0.1
 * @Description:
 */
@Data
public class A {
    private Integer id;
    private String desc;

    public A() {
    }

    public A(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static class B{
        public Integer id;
        public String desc;
    }
}
