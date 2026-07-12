package com.homelander.context;

/**
 * ClassName:BaseContext
 * Package:com.homelander.context
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/11 16:39
 * @Version 1.0
 */
public class BaseContext {
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    // set方法
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    // get方法
    public static Long getCurrentId(){
        return threadLocal.get();
    }

    public static void removeCurrentId(){
        threadLocal.remove();
    }
}

