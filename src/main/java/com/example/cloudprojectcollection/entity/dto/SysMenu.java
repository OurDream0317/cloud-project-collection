package com.example.cloudprojectcollection.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wwx
 * @createTime: 2022年07月14日
 * @version: 0.0.1
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu {
    private Integer id;
    private Integer parentId;
    private String name;
    private Integer weight;
}
