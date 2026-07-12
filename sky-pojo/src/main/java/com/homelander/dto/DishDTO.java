package com.homelander.dto;

import com.homelander.entity.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:DishDTO
 * Package:com.homelander.dto
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 11:19
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO implements Serializable {
    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private String image;
    private String description;
    private Integer status;
    // 默认值new ArrayList<>()避免空指针
    private List<DishFlavor> flavors = new ArrayList<>();
}
