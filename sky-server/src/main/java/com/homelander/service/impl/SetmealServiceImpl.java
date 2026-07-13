package com.homelander.service.impl;

import com.homelander.dto.SetMealDto;
import com.homelander.entity.Setmeal;
import com.homelander.entity.SetmealDish;
import com.homelander.mapper.SetmealDishMapper;
import com.homelander.mapper.SetmealMapper;
import com.homelander.result.Result;
import com.homelander.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:SetmealServiceImpl
 * Package:com.homelander.service.impl
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 15:51
 * @Version 1.0
 */
@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private static SetmealMapper setmealMapper;

    @Autowired
    private static SetmealDishMapper setmealDishMapper;

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setMealDto
     */
    @Override
    public void saveWithDish(SetMealDto setMealDto) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setMealDto,setmeal);

        // 向套餐表插入数据
        setmealMapper.insert(setmeal);

        //获取生成的套餐id
        Long mealId = setmeal.getId();

        // 关联菜品
        List<SetmealDish> setmealDishes = setMealDto.getSetmealDishes();
        if (setmealDishes !=null && setmealDishes.size() >0){
            // 关联套餐
            for (SetmealDish dish : setmealDishes) {
                dish.setSetmealId(mealId);
            }
            setmealDishMapper.insertBatch(setmealDishes); // 批量插入
        }
    }
}
