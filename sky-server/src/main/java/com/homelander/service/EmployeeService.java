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
}
