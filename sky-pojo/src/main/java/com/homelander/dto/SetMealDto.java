package com.homelander.dto;

import com.homelander.entity.SetmealDish;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:SetMealDto
 * Package:com.homelander.dto
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 15:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetMealDto {
    private Long id;
    private Long categoryId;
    private String name;
    private BigDecimal price;
    private Integer status;
    private String description;
    private String image;
    private List<SetmealDish> setmealDishes = new ArrayList<>();
}
