package com.homelander.mapper;

import com.homelander.entity.DishFlavor;

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
    void insertBatch(List<DishFlavor> flavors);
}
