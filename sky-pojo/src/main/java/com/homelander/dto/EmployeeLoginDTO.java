package com.homelander.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName:EmployeeLoginDTO 用于登录请求的临时对象
 * Package:com.homelander.dto
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 15:43
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginDTO implements Serializable {
    private String username;
    private String password;
}
