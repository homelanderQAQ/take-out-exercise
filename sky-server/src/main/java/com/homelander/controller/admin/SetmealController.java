package com.homelander.controller.admin;

import com.homelander.dto.SetMealDto;
import com.homelander.dto.SetmealPageQueryDTO;
import com.homelander.result.PageResult;
import com.homelander.result.Result;
import com.homelander.service.SetmealService;
import com.homelander.vo.SetmealVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:SetmealController
 * Package:com.homelander.controller.admin
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 15:49
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api("套餐相关接口")
public class SetmealController {
    @Autowired
    private static SetmealService setmealService;

    /**
     * 新增套餐
     * @param setMealDto
     * @return
     */
    @PostMapping
    public Result save(@RequestBody SetMealDto setMealDto){
        log.info("新增套餐:{}",setMealDto);
        setmealService.saveWithDish(setMealDto);
        return Result.success();
    }

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询：{}",setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批量删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result deltet(@RequestParam List<Long> ids){
        log.info("删除菜品的ids：{}",ids);
        setmealService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改套餐
     * @param setMealDto
     * @return
     */
    public Result update (@RequestBody SetMealDto setMealDto){
        log.info("修改的套餐:{}",setMealDto);
        setmealService.updateWithDish(setMealDto);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id){
        log.info("要查找的套餐id：{}",id);
        SetmealVO setmealVO = setmealService.getById(id);
        return Result.success(setmealVO);
    }

    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("起售或停售菜品：{},{}",status,id);
        setmealService.startOrStop(status,id);
        return Result.success();
    }
}
