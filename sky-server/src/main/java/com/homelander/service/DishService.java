package com.homelander.service;

import com.homelander.dto.DishDTO;

/**
 * ClassName:DishService
 * Package:com.homelander.service
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 11:45
 * @Version 1.0
 */
public interface DishService {
    /**
     * 添加菜品服务层接口
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);
}
