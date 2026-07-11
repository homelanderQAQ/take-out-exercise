package com.homelander.service;

import com.homelander.dto.EmployeeDTO;
import com.homelander.dto.EmployeeLoginDTO;
import com.homelander.dto.EmployeePageQueryDTO;
import com.homelander.entity.Employee;
import com.homelander.result.PageResult;
import com.homelander.vo.EmployeeLoginVO;

/**
 * ClassName:EmployeeService 服务层方法
 * Package:com.homelander.service
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 17:28
 * @Version 1.0
 */
public interface EmployeeService {


    /**
     * 登录业务
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工服务层方法
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询服务层方法
     * @param employeePageQueryDTO name、page、pagesize
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用或者禁用员工账号
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    Employee getByid(Long id);

    /**
     * 更新员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
