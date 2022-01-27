package com.eugeneli.store.controller.ex;

/**
 * @ClassName: FileIOException
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/1/27
 * @Time: 20:49
 *
 * 上传文件时读写异常
 */
public class FileIOException extends FileUploadException{
    public FileIOException() {
        super();
    }

    public FileIOException(String message) {
        super(message);
    }

    public FileIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIOException(Throwable cause) {
        super(cause);
    }

    protected FileIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
