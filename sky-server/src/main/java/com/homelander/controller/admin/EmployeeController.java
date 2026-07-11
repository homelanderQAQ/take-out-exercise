package com.homelander.controller.admin;

import com.homelander.dto.EmployeeDTO;
import com.homelander.dto.EmployeeLoginDTO;
import com.homelander.dto.EmployeePageQueryDTO;
import com.homelander.entity.Employee;
import com.homelander.result.PageResult;
import com.homelander.result.Result;
import com.homelander.service.EmployeeService;
import com.homelander.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:EmployeeController
 * Package:com.homelander.controller.admin
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 17:35
 * @Version 1.0
 */
@RestController //@RestController = @Controller + @ResponseBody
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {
    @Autowired
    private static EmployeeService employeeService;


    /**
     * 登录业务接口
     * @param employeeLoginDTO 接收用户名和密码
     * @return  Result<EmployeeLoginVO> 用Resutl封装的用户信息数据其中只有 id,name,username,token数据
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        // 1.打印日志
        log.info("员工登录:{}",employeeLoginDTO);

        // 2. 调用 employeeService.login(employeeLoginDTO)
        Employee employee = employeeService.login(employeeLoginDTO);

        // 3. 生成 JWT（先略过，第六阶段做）
        // TODO


        // 4. 组装 EmployeeLoginVO 返回
        EmployeeLoginVO employeeLoginVO =EmployeeLoginVO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .username(employee.getUsername())
                .token(null)
                .build();
        return Result.success(employeeLoginVO);
    }

    /**
     * 推出业务代码
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout(){
        return Result.success();
    }

    /**
     * 新增员工控制层方法
     * @param employeeDTO
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody EmployeeDTO employeeDTO){
        // 1.打印日志
        log.info("新增员工：{}",employeeDTO);
        // 2.调用服务层的方法
        employeeService.save(employeeDTO);
        // 3 return Result.success()
        return Result.success();
    }

    /**
     * 分页查询控制层方法
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    //请求参数不加 @RequestBody
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("员工分页查询,参数为：{}",employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用或者禁用用户代码
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("启用禁用员工账号:{},{}",status,id);
        employeeService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 根据员工id查找员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id){
        log.info("员工id：{}",id);
        Employee employee = employeeService.getByid(id);

        return Result.success(employee);
    }

    /**
     * 更新员工信息
     * @param employeeDTO
     * @return
     */
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        employeeService.update(employeeDTO);
        return Result.success();
    }
}
