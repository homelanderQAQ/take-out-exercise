package com.homelander.service;

import com.homelander.dto.SetMealDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
