package com.homelander.mapper;

import com.github.pagehelper.Page;
import com.homelander.dto.EmployeePageQueryDTO;
import com.homelander.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName:EmployeeMapper
 * Package:com.homelander.mapper
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 16:18
 * @Version 1.0
 */
@Mapper
public interface EmployeeMapper {
    /**
     * 按照用户名查询用户
     * @param username 雇员名字
     * @return 返回一个Employee对象
     */
    @Select("select * from sky_take_out.employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 根据id查找
     * @param id 用户id
     * @return 返回一个Employee对象
     */
    @Select("select * from sky_take_out.employee where id = #{id}")
    Employee getById(Long id);

    /**
     * 添加用户
     * @param employee 用户employee对象
     */
    @Insert("insert into sky_take_out.employee (name, username, password, phone, sex, id_number, create_time, update_time, create_user, update_user) " +
            "values " +
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Employee employee);


    /**
     * 根据用户id动态更新用户数据
     * @param employee
     */
    void update(Employee employee);

    /**
     * 分页+动态查询
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
