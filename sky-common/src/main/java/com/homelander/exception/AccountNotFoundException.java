package com.homelander.exception;

/**
 * ClassName:AccountNotFoundException
 * Package:com.homelander.exception
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 17:25
 * @Version 1.0
 */
public class AccountNotFoundException extends BaseException{
    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }
}
