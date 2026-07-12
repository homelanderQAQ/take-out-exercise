package com.homelander.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * ClassName:JwtProperties
 * Package:com.homelander.properties
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/11 16:18
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "homelander.jwt")
@Data
public class JwtProperties {
    /**
     * 管理端员工生成jwt令牌相关配置
     */
    private String adminSecretKey;
    private Long adminTtl;
    private String adminTokenName;


}
