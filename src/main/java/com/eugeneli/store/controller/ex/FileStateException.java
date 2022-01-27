package com.eugeneli.store.controller.ex;

/**
 * @ClassName: FileStateException
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/1/27
 * @Time: 20:48
 * 文件状态异常
 */
public class FileStateException extends FileUploadException{

    public FileStateException() {
        super();
    }

    public FileStateException(String message) {
        super(message);
    }

    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }

    protected FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
