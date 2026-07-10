package com.homelander.service;

import com.homelander.dto.EmployeeLoginDTO;
import com.homelander.entity.Employee;
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
}
