package com.homelander.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ClassName:Employee
 * Package:com.homelander.entity
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 15:37
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable{
    private Long id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String idNumber;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
}
