package com.eugeneli.store.controller.ex;

/**
 * @ClassName: FileEmptyException
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/1/27
 * @Time: 20:46
 * 文件上传为空时的异常
 */
public class FileEmptyException extends FileUploadException{

    public FileEmptyException() {
        super();
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    protected FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
