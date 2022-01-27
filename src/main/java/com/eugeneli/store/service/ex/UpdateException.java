package com.eugeneli.store.service.ex;

/**
 * @ClassName: UpdateException
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/1/23
 * @Time: 0:59
 * update在更新数据的时候 有可能产生未知的异常 将来映射到sql语句上
 */



public class UpdateException extends ServiceException{

    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
