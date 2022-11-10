package com.example.cloudprojectcollection.OptionTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

/**
 * @Author: Wwx
 * @createTime: 2022年09月07日
 * @version: 0.0.1
 * @Description:   Optional类是Java8为了解决null值判断问题，
 *                 借鉴google guava类库的Optional类而引入的一个同名Optional类，
 *                 使用Optional类可以避免显式的null值判断（null的防御性检查），
 *                 避免null导致的NPE（NullPointerException）。
 */
@RunWith(JUnit4.class)
public class OptionalTest {

    @Test
    public void m1(){
        A a = new A();
        // 1、创建一个包装对象值为空的Optional对象
        Optional<String> optStr = Optional.empty();
        // 2、创建包装对象值非空的Optional对象
        Optional<String> optStr1 = Optional.of("optional");
        // 3、创建包装对象值允许为空的Optional对象
        Optional<A> optStr2 = Optional.ofNullable(a);

        A s = optStr2.orElseGet(() -> {
            return new A(1,"wwx");
        });
        System.out.println(s+"===============");

        System.out.println(optStr2);
    }

    @Test
    public void m2(){
        Optional<A> optionalA = Optional.of(new A(1,"wwx"));
        Optional<Optional<Integer>> integer = optionalA.map(a -> Optional.of(a.getId()));
        /**
         * 跟map()方法不同的是，
         * 入参Function函数的返回值类型为Optional类型，
         * 而不是U类型，这样flatMap()能将一个二维的Optional对象映射成一个一维的对象
         */
        Optional<Integer> integer1 = optionalA.flatMap(a -> Optional.of(a.getId()));
        System.out.println(optionalA);
        System.out.println(integer.get());
        System.out.println(integer1.get());
    }
}
