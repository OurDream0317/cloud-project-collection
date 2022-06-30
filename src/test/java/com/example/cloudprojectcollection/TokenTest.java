package com.example.cloudprojectcollection;

import com.example.cloudprojectcollection.util.TokenUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Author: Wwx
 * @createTime: 2022年06月30日
 * @version: 0.0.1
 * @Description: token测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenTest {

    @Test
    public void test() {
        Map<String, String> stringStringMap = TokenUtils.generateRasKey();
        System.out.println(stringStringMap);
    }
}
