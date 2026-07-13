package com.homelander.mapper;

import com.github.pagehelper.Page;
import com.homelander.annotation.AutoFill;
import com.homelander.dto.SetmealPageQueryDTO;
import com.homelander.entity.Setmeal;
import com.homelander.enumeration.OperationType;
import com.homelander.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 添加套餐
     * @param setmeal
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 动态分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    @Select("select * from setmeal where id =#{id}")
    Setmeal getById(Long id);

    @Delete("delete from dish where id = #{setmealId}")
    void deleteById(Long setmealId);
}
