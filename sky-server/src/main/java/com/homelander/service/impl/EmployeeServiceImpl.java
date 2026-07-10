package com.homelander.service.impl;

import com.homelander.constant.MessageConstant;
import com.homelander.constant.StatusConstant;
import com.homelander.dto.EmployeeLoginDTO;
import com.homelander.entity.Employee;
import com.homelander.exception.AccountLockedException;
import com.homelander.exception.AccountNotFoundException;
import com.homelander.exception.PasswordErrorException;
import com.homelander.mapper.EmployeeMapper;
import com.homelander.service.EmployeeService;
import com.homelander.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * ClassName:EmployeeServiceImpl 服务层业务实现
 * Package:com.homelander.service.impl
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 17:49
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private static EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        // 1. 根据用户名查询数据库
        Employee employee = employeeMapper.getByUsername(username);

        // 2. 查不到 → 抛 `AccountNotFoundException`
        if (employee == null ){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        // 3. 密码用 MD5 加密后比对（`DigestUtils.md5DigestAsHex`）
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 4. 不匹配 → 抛 `PasswordErrorException`
        if (!employee.getPassword().equals(password)){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        // 5. 账号状态为 0（禁用）→ 抛 `AccountLockedException`
        if (employee.getStatus() == StatusConstant.DISABLE){
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        // 6. 全部通过 → 返回 Employee 对象
        return employee;
    }
}
