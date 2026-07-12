package com.homelander.controller.admin;

import com.homelander.dto.DishDTO;
import com.homelander.dto.DishPageQueryDTO;
import com.homelander.result.PageResult;
import com.homelander.result.Result;
import com.homelander.service.DishService;
import com.homelander.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:DishController
 * Package:com.homelander.controller.admin
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 11:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品相关接口")
public class DishController {
    @Autowired
    private static DishService dishService;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping()
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品:{}",dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询：{}",dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 菜品批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("菜品批量删除")
    public Result delete(@RequestParam List<Long> ids){
        log.info("菜品批量删除");
        dishService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据id获取菜品信息
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据id查询菜品；{}",id);
        com.homelander.vo.DishVO dishVO =dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜单
     * @param dishDTO
     * @return
     */
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜单：{}",dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }
}
