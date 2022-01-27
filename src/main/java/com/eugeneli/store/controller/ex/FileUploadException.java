package com.eugeneli.store.controller.ex;

/**
 * @ClassName: FileUploadException
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/1/27
 * @Time: 20:43
 * 文件上传相关异常的基类
 */
public class FileUploadException extends RuntimeException{


    public FileUploadException() {
        super();
    }


    public FileUploadException(String message) {
        super(message);
    }


    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }


    public FileUploadException(Throwable cause) {
        super(cause);
    }


    protected FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
