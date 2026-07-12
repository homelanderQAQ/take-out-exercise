package com.homelander.service.impl;

import com.homelander.dto.DishDTO;
import com.homelander.entity.Dish;
import com.homelander.entity.DishFlavor;
import com.homelander.mapper.DishFlavorMapper;
import com.homelander.mapper.DishMapper;
import com.homelander.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:DishServiceImpl
 * Package:com.homelander.service.impl
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 11:46
 * @Version 1.0
 */
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private static DishMapper dishMapper;

    @Autowired
    private static DishFlavorMapper dishFlavorMapper;

    /**
     * 添加菜品服务层实现代码
     * @param dishDTO
     */
    @Transactional // 多表查询需要添加事务
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {

        //1. Dish dish = new Dish()
        Dish dish = new Dish();

        //2. BeanUtils.copyProperties(dishDTO, dish)     // DTO → Entity
        BeanUtils.copyProperties(dishDTO,dish);

        //3. dishMapper.insert(dish)                      // 插菜品，主键自动回填
        dishMapper.insert(dish);

        //4. Long dishId = dish.getId()                   // 拿到自增主键
        Long dishId = dish.getId();

        //5. 取出 dishDTO.getFlavors()
        List<DishFlavor> flavors = dishDTO.getFlavors();

        /*6. 如果 flavors 不为空：
                - flavors.forEach(f -> f.setDishId(dishId)) // 给每个口味关联菜品ID
                - dishFlavorMapper.insertBatch(flavors)      // 批量插入口味*/
        if (flavors != null || flavors.size() >0){
            flavors.forEach(flavor-> flavor.setDishId(dishId));
            dishFlavorMapper.insertBatch(flavors); // 批量插入
        }
    }
}
