package com.homelander.mapper;

import com.homelander.annotation.AutoFill;
import com.homelander.entity.DishFlavor;
import com.homelander.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName:DishFlavorMapper
 * Package:com.homelander.mapper
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 12:10
 * @Version 1.0
 */
public interface DishFlavorMapper {
    /**
     * 批量插入口味数据
     * @param flavors
     */
    @AutoFill(value = OperationType.INSERT)
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 批量删除口味数据
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * 根据id过去口味信息
     * @param dishId
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{disId}")
    List<DishFlavor> getByDishId(Long dishId);

    @Delete("delete from dish_flavor where dish_id = #{dishId} ")
    void deleteByDishId(Long dishId);
}
