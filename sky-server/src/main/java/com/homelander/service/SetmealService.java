package com.homelander.service;

import com.homelander.dto.SetMealDto;
import com.homelander.dto.SetmealPageQueryDTO;
import com.homelander.result.PageResult;
import com.homelander.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:SetmealService
 * Package:com.homelander.service
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 15:50
 * @Version 1.0
 */
public interface SetmealService {

    /**
     * 添加套餐
     * @param setMealDto
     */
    void saveWithDish(SetMealDto setMealDto);

    /**
     * 分页查询服务层代码
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /***
     * 修改套餐
     * @param setMealDto
     */
    void updateWithDish(SetMealDto setMealDto);

    /**
     * 根据id查找套餐
     * @param id
     * @return
     */
    SetmealVO getById(Long id);
}
