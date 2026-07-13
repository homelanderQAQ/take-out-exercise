package com.homelander.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName:DishPageQueryDTO
 * Package:com.homelander.dto
 * Description: 分页查询
 *
 * @Author Heisenberg
 * @Create 2026/7/12 11:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishPageQueryDTO implements Serializable {
    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer categoryId;
    private Integer status;
}
