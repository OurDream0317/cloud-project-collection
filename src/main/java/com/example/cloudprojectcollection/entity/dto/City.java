package com.example.cloudprojectcollection.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wwx
 * @createTime: 2022年07月12日
 * @version: 0.0.1
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("city")
public class City {
    public Integer id;
    public String name;
    public String countrycode;
    public String district;
    public String population;
}
