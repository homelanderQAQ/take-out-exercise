package com.homelander.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.homelander.annotation.AutoFill;
import com.homelander.constant.MessageConstant;
import com.homelander.constant.PasswordConstant;
import com.homelander.constant.StatusConstant;
import com.homelander.dto.EmployeeDTO;
import com.homelander.dto.EmployeeLoginDTO;
import com.homelander.dto.EmployeePageQueryDTO;
import com.homelander.entity.Employee;
import com.homelander.enumeration.OperationType;
import com.homelander.exception.AccountLockedException;
import com.homelander.exception.AccountNotFoundException;
import com.homelander.exception.PasswordErrorException;
import com.homelander.mapper.EmployeeMapper;
import com.homelander.result.PageResult;
import com.homelander.service.EmployeeService;
import com.homelander.vo.EmployeeLoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 登录业务
     * @param employeeLoginDTO
     * @return
     */
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

    /**
     * 新增员工业务
     * @param employeeDTO
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {
        //1. `BeanUtils.copyProperties(dto, employee)` 拷贝属性
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);

        //2. 设置默认状态为启用（1）
        employee.setStatus(StatusConstant.ENABLE);

        //3. 设置默认密码为 `123456` 的 MD5 值
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

       /* //4.设置修改时间和修改人id
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(10L);
        employee.setUpdateUser(10L);*/

        //5. 调用 `employeeMapper.insert(employee)`
        employeeMapper.insert(employee);

    }

    /**
     * 分页查询实现代码
     * @param employeePageQueryDTO name、page、pagesize
     * @return
     */
    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //1. `PageHelper.startPage(dto.getPage(), dto.getPageSize())` 开启分页
        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());

        //2. 调用 `employeeMapper.pageQuery(dto)` 得到 `Page<Employee>`
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        long total = page.getTotal();
        List<Employee> records = page.getResult();

        //3. `new PageResult(page.getTotal(), page.getResult())` 返回
        return new PageResult(total,records);
    }

    /**
     * 启用或者禁用员工
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        // update employee set status = ? where id = ?
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();

        /*employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(10L);*/

        employeeMapper.update(employee);
    }

    /**
     * 根据员工id获取员工信息
     * @param id
     * @return
     */
    @Override
    public Employee getByid(Long id) {
        Employee employee =employeeMapper.getById(id);
        return  employee;
    }

    /**
     * 更新用户信息
     * @param employeeDTO
     */
    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);

       /* employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(10L);*/

        employeeMapper.update(employee);
    }
}
