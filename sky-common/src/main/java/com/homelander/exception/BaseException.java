package com.homelander.exception;

/**
 * ClassName:BaseException
 * Package:com.homelander.exception
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 17:22
 * @Version 1.0
 */
public class BaseException extends RuntimeException{
    public BaseException(){}

    public BaseException(String msg){ super(msg);}
}
