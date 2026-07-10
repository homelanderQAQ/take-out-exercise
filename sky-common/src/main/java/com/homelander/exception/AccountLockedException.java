package com.homelander.exception;

/**
 * ClassName:AccountLockedException
 * Package:com.homelander.exception
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/10 17:27
 * @Version 1.0
 */
public class AccountLockedException extends BaseException{
    public AccountLockedException() {
    }

    public AccountLockedException(String msg) {
        super(msg);
    }
}
