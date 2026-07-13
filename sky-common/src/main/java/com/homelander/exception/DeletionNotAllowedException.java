package com.homelander.exception;

/**
 * ClassName:DeletionNotAllowedException
 * Package:com.homelander.exception
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/12 13:33
 * @Version 1.0
 */
public class DeletionNotAllowedException extends BaseException {
    public DeletionNotAllowedException() {
    }

    public DeletionNotAllowedException(String message) {
        super(message);
    }
}
