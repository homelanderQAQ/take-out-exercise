package com.homelander.service;

import com.homelander.dto.DishDTO;
import com.homelander.dto.DishPageQueryDTO;
import com.homelander.entity.Dish;
import com.homelander.result.PageResult;
import com.homelander.vo.DishVO;

import java.util.List;

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

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 菜品批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询菜品信息
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 更新菜单
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);
}
