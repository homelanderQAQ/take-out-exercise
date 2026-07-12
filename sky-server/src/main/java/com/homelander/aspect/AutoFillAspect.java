package com.homelander.aspect;

import com.homelander.annotation.AutoFill;
import com.homelander.constant.AutoFillConstant;
import com.homelander.context.BaseContext;
import com.homelander.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * ClassName:AutoFillAspect
 * Package:com.homelander.aspect
 * Description:自定义切面，实现公共字段自动填充处理逻辑
 *  *
 * @Author Heisenberg
 * @Create 2026/7/11 17:12
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    // 切入点             返回类型             包.类.方法(参数)         包含@AutoFill注解的方法
    @Pointcut("execution(* com.homelander.mapper.*.*(..)) && @annotation(com.homelander.annotation.AutoFill)")
    public void autoFillPointCut(){}

    // 前置通知，在通知中进行公共字段的自动填充
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("开始进行公共字段自动填充....");

        //1. 获取方法上的 `@AutoFill` 注解，知道是 INSERT 还是 UPDATE
        //MethodSignature类里面才有getMethod方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType value = autoFill.value(); // 获取数据库操作类型

        //2. 获取方法的第一个参数（即要插入/更新的 Entity 对象）
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0){
            return;
        }
        Object entity = args[0];
        //3. 获取当前时间 `LocalDateTime.now()` 和当前用户 ID `BaseContext.getCurrentId()`
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        //4. 用反射调用 setter 方法赋值：
        //- INSERT：设置 createTime, updateTime, createUser, updateUser
        //- UPDATE：设置 updateTime, updateUser
        if (value == OperationType.INSERT){
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 通过反射为对象属性赋值
                setCreateTime.invoke(entity,now);
                setCreateUser.invoke(entity,currentId);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (value == OperationType.UPDATE) {
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
