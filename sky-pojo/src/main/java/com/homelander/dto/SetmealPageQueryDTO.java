package com.homelander.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName:SetmealPageQueryDTO
 * Package:com.homelander.dto
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/13 16:13
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetmealPageQueryDTO implements Serializable {
    private Integer page;
    private Integer pageSize;
    private String name; // 套餐名称
    private Long categoryId; // 分类
    private Integer status;
}
