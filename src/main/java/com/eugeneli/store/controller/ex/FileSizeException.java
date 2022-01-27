package com.eugeneli.store.controller.ex;

/**
 * @ClassName: FileSizeException
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/1/27
 * @Time: 20:47
 * 上传的文件大小超出了限制值
 */
public class FileSizeException extends FileUploadException{

    public FileSizeException() {
        super();
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    protected FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
