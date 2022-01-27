package com.eugeneli.store.controller.ex;

/**
 * @ClassName: FileTypeException
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/1/27
 * @Time: 20:47
 * 文件的类型超出了限制出现了异常
 */
public class FileTypeException extends FileUploadException{
    public FileTypeException() {
        super();
    }

    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeException(Throwable cause) {
        super(cause);
    }

    protected FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
