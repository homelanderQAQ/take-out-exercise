package com.homelander.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName:DishFlavor
 * Package:com.homelander.entity
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 11:18
 * @Version 1.0
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishFlavor implements Serializable {
    private Long id;
    private Long dishId;
    private String name;
    private String value;
}
