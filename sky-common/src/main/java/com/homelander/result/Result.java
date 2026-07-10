package com.homelander.result;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName:Result
 * Package:com.homelander.result
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 15:54
 * @Version 1.0
 */
@Data
public class Result<T> implements Serializable {
    private Integer code; // 1=成功, 0=失败
    private String msg;   // 提示信息
    private  T data;      // 泛型数据

    //提供静态方法 声明「这是一个泛型方法」，定义一个类型变量 T

    //成功返回无数据
    public static <T> Result<T> success(){
        Result<T> result = new Result<T>();
        result.code = 1;
        return  result;
    }

    // 成功返回带数据
    public static <T> Result<T> success(T object){
        Result<T> result = new Result<T>();
        result.code = 1;
        result.data = object;
        return result;
    }

    // 失败 返回失败信息
    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<T>();
        result.code =0;
        result.msg = msg;
        return result;
    }
}
