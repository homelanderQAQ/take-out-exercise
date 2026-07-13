package com.homelander.mapper;

import com.github.pagehelper.Page;
import com.homelander.annotation.AutoFill;
import com.homelander.dto.DishPageQueryDTO;
import com.homelander.entity.Dish;
import com.homelander.enumeration.OperationType;
import com.homelander.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName:DishMapper
 * Package:com.homelander.mapper
 * Description: 菜单操作Mapper层代码
 *
 * @Author Heisenberg
 * @Create 2026/7/12 11:51
 * @Version 1.0
 */
@Mapper
public interface DishMapper {

    /**
     * 新增菜品
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 批量删除菜品
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 动态更新菜品
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    @Select("select count(id) from dish where category_id =#{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 根据分类id查询菜品
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);
}
