package com.homelander.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.homelander.constant.MessageConstant;
import com.homelander.constant.StatusConstant;
import com.homelander.dto.SetMealDto;
import com.homelander.dto.SetmealPageQueryDTO;
import com.homelander.entity.Dish;
import com.homelander.entity.Setmeal;
import com.homelander.entity.SetmealDish;
import com.homelander.exception.DeletionNotAllowedException;
import com.homelander.mapper.SetmealDishMapper;
import com.homelander.mapper.SetmealMapper;
import com.homelander.result.PageResult;
import com.homelander.result.Result;
import com.homelander.service.SetmealService;
import com.homelander.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());

        Page<SetmealVO> setmealVOPage =  setmealMapper.pageQuery(setmealPageQueryDTO);

        return new PageResult(setmealVOPage.getTotal(),setmealVOPage.getResult());
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteBatch(List<Long> ids) {
        //起售中的套餐不能删除
        ids.forEach(id->{
            Setmeal setmeal =  setmealMapper.getById(id);
            if (setmeal.getStatus() == StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });
        ids.forEach(setmealId ->{
            setmealMapper.deleteById(setmealId);  // 删除套餐
            setmealDishMapper.deleteBySetmealId(setmealId); // 删除菜品
        });
    }

    /**
     * 修改套餐
     * @param setMealDto
     */
    @Transactional
    @Override
    public void updateWithDish(SetMealDto setMealDto) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setMealDto,setmeal);

        setmealMapper.update(setmeal);

        Long setmealId = setmeal.getId();
        setmealDishMapper.deleteBySetmealId(setmealId);

        // 取出菜品
        List<SetmealDish> setmealDishes = setMealDto.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });
        // 重新插入套餐和菜品的关联关系
        setmealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * 根据id查找套餐
     * @param id
     * @return
     */
    @Override
    public SetmealVO getById(Long id) {
        Setmeal setmeal = setmealMapper.getById(id);

        List<SetmealDish> setmealDishes =  setmealDishMapper.getBySetmealId(id);

        SetmealVO setmealVO = new SetmealVO();

        BeanUtils.copyProperties(setmeal,setmealVO);
        setmealVO.setSetmealDishes(setmealDishes);

        return setmealVO;
    }
}
