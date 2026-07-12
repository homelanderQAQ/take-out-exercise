package com.homelander.mapper;

import com.homelander.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:DishMapper
 * Package:com.homelander.mapper
 * Description: 菜单操作Mapper层代码
 *
 * @Author Heisenberg
 * @Create 2026/7/12 11:51
 * @Version 1.0
 */
@Mapper
public interface DishMapper {

    /**
     * 新增菜品
     * @param dish
     */
    void insert(Dish dish);

}
