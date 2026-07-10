package com.homelander.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName:EmployeeLoginVO
 * Package:com.homelander.vo
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 15:51
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeLoginVO implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String token;
}
