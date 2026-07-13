package com.homelander.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.homelander.annotation.AutoFill;
import com.homelander.constant.MessageConstant;
import com.homelander.constant.StatusConstant;
import com.homelander.dto.DishDTO;
import com.homelander.dto.DishPageQueryDTO;
import com.homelander.entity.Dish;
import com.homelander.entity.DishFlavor;
import com.homelander.enumeration.OperationType;
import com.homelander.exception.DeletionNotAllowedException;
import com.homelander.mapper.DishFlavorMapper;
import com.homelander.mapper.DishMapper;
import com.homelander.mapper.SetmealDishMapper;
import com.homelander.result.PageResult;
import com.homelander.service.DishService;
import com.homelander.vo.DishVO;
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

    @Autowired
    private static SetmealDishMapper setmealDishMapper;

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

    /**
     * 分页查询服务层代码
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        int page = dishPageQueryDTO.getPage();
        int pageSize = dishPageQueryDTO.getPageSize();

        // ThreadLocal 存分页参数
        PageHelper.startPage(page,pageSize);

        // 紧随其后的 SQL 被拦截改写
        Page<DishVO> voPage = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(voPage.getTotal(),voPage.getResult());
    }

    /**
     * 批量删除菜品服务层实现代码
     * @param ids
     */
    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        //【校验 1 — 是否有起售菜品？】
        for (Long id : ids) {
           Dish dish =  dishMapper.getById(id);
           if (dish.getStatus() == StatusConstant.ENABLE){
               // 起售状态不能删除
               throw  new DeletionNotAllowedException(MessageConstant.DISH_NO_SALE);
           }
        }
        //【校验 2 — 是否被套餐关联？】
        List<Long> setmealIds =setmealDishMapper.getSetmealIdsByDishIds(ids);
        if (setmealIds != null && setmealIds.size() >0){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        //【执行删除】
        // 批量删除菜品
        dishMapper.deleteByIds(ids);
        // 批量删除口味
        dishFlavorMapper.deleteByDishIds(ids);
    }

    /**
     * 根据id获取菜品信息 用于修改页面回显数据
     * @param id
     * @return
     */
    @Override
    public DishVO getByIdWithFlavor(Long id) {
        // 1. Dish dish = dishMapper.getById(id)              // 查菜品基本信息
        Dish dish = dishMapper.getById(id);

        // 2. List<DishFlavor> flavors = dishFlavorMapper.getByDishId(id)  // 查口味列表
        List<DishFlavor> flavors = dishFlavorMapper.getByDishId(id);

        // 3. DishVO dishVO = new DishVO()
        DishVO dishVO = new DishVO();

        // 4. BeanUtils.copyProperties(dish, dishVO)          // 搬运同名字段
        BeanUtils.copyProperties(dish,dishVO);

        // 5. dishVO.setFlavors(flavors)                       // 单独 set 口味
        dishVO.setFlavors(flavors);

        // 6. return dishVO
        return dishVO;
    }

    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        // 1. Dish dish = new Dish()
        Dish dish = new Dish();

        // 2. BeanUtils.copyProperties(dishDTO, dish)          // DTO → Entity
        BeanUtils.copyProperties(dishDTO,dish);

        // 3. dishMapper.update(dish)                           // 动态更新菜品基本信息
        dishMapper.update(dish);

        // 4. dishFlavorMapper.deleteByDishId(dishDTO.getId())  // 删掉旧口味
        dishFlavorMapper.deleteByDishId(dishDTO.getId());

        // 5. 取出 dishDTO.getFlavors()
        List<DishFlavor> flavors = dishDTO.getFlavors();

        // 6. 如果 flavors 不为空：
        // - flavors.forEach(f -> f.setDishId(dishDTO.getId()))
        // - dishFlavorMapper.insertBatch(flavors)           // 插入新口味
        if (flavors!= null && flavors.size()>0){
            flavors.forEach(flavor -> flavor.setDishId(dish.getId()));
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> list(Long categoryId) {
        Dish dish = Dish.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();

        return dishMapper.list(dish);
    }
}
