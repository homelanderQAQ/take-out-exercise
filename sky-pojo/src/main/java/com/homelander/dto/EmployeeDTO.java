package com.homelander.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName:EmployeeDTO 用于新增/修改员工的临时对象
 * Package:com.homelander.dto
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 15:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String phone;
    private String sex;
    private String idNumber;
}
