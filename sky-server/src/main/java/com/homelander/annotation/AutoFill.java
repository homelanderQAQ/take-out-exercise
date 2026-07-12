package com.homelander.annotation;

import com.homelander.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName:AutoFill
 * Package:com.homelander.annotation
 * Description:自动填充机制
 *对这`create_time`、`update_time`、`create_user`、`update_user` 这四个字段实现自动填充
 * 保留策略: RUNTIME
 * 目标: METHOD
 * @Author Heisenberg
 * @Create 2026/7/11 17:06
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    // 数据库操作类型：UPDATE INSERT
    OperationType value();
}
