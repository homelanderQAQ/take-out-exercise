package com.homelander.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ClassName:SetmealDish
 * Package:com.homelander.entity
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 16:02
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetmealDish implements Serializable {
    private Long id;
    // 套餐id
    private Long setmealId;
    // 菜品id
    private Long dishId;

    private String name;

    private BigDecimal price;

    // 份数
    private Integer copies;
}
