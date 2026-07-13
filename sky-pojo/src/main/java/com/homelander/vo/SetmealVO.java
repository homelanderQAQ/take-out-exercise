package com.homelander.vo;

import com.homelander.entity.Dish;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:SetmealVO
 * Package:com.homelander.vo
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/13 16:28
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetmealVO implements Serializable {
    private Long id;
    private Long categoryId;
    private String name;
    private BigDecimal price;
    private Integer status;
    private String description;
    private String image;
    private LocalDateTime updateTime;
    private String categoryName;
    private List<Dish> dishes = new ArrayList<>();
}
