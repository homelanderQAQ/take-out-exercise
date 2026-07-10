package com.homelander.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName:EmployeePageQueryDTO 用于分页查询的临时对象
 * Package:com.homelander.dto
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 15:49
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePageQueryDTO implements Serializable {
    private String name; // 员工姓名
    private int page;
    private int pageSize;
}
