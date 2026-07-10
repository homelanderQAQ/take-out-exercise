package com.homelander.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName:PageResult
 * Package:com.homelander.result
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 16:13
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {
    private Long total;
    private List records;
}
