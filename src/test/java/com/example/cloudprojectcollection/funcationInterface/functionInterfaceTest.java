package com.example.cloudprojectcollection.funcationInterface;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author: Wwx
 * @createTime: 2022年08月18日
 * @version: 0.0.1
 * @Description:
 */

public class functionInterfaceTest {


    @Test
    public void method1() {
        String s = (String) null;
        if(s.equals("0")){
            System.out.println("+++++++++++++");
        }
        System.out.println("-------------Supplier--------------------");
        String string = getString(() -> "王卫翔");
        System.out.println(string);
        System.out.println("-----------------Consumer-------------------------");
        acceptMethod("王卫翔",name -> System.out.println(name));
    }


    public String getString(Supplier<String> str) {
        return str.get();
    }


    public void acceptMethod(String s, Consumer<String> str) {
        str.accept(s);
    }
}
