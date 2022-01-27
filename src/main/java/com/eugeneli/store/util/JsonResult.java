package com.eugeneli.store.util;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {


    /**
     * 状态码
     */
    private Integer state;

    /**
     * 状态描述信息
     */
    private String message;

    /**
     * 返回数据类型
     */
    private E data;


    public JsonResult() {
        super();
    }

    public JsonResult(Integer state) {
        super();
        this.state = state;
    }

    public JsonResult(Throwable e){
        super();
        //异常时的异常信息
        this.message=e.getMessage();
    }
    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(String message, E data) {
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
