package com.homelander.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:SetmealDishMapper
 * Package:com.homelander.mapper
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 13:37
 * @Version 1.0
 */
@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询对应的套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
}
