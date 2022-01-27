package com.eugeneli.store.service.ex;

/**
 * 业务层异常的基类  业务层产生未知的异常
 * 根据业务层不同的功能来详细定义异常的类型 统一的去继承ServiceException
 */
public class ServiceException extends RuntimeException{

    public ServiceException() {
        super();
    }

    //只抛出异常信息
    public ServiceException(String message) {
        super(message);
    }

    //抛出异常信息还有异常对象
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
