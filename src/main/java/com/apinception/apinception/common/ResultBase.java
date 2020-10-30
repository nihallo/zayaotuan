package com.apinception.apinception.common;

import java.io.Serializable;

public class ResultBase<T> implements Serializable {

    private static final long serialVersionUID = 2057948781441813066L;
    private boolean isSuccess;
    private String code;
    private String msg;
    private T value;

    public ResultBase() {
        this.isSuccess = false;
    }

    public ResultBase(T value) {
        this.isSuccess = true;
        this.value = value;
    }

    public ResultBase(String msg, String code) {
        this(false, msg, code);
    }

    public ResultBase(boolean success, String msg, String code) {
        this.isSuccess = false;
        this.isSuccess = success;
        this.msg = msg;
        this.code = code;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public ResultBase<T> fail(String code) {
        this.setSuccess(false);
        this.setCode(code);
        return this;
    }

    public ResultBase<T> success(T responseDTo) {
        this.setSuccess(true);
        this.setValue(responseDTo);
        return this;
    }

    public ResultBase<T> mapper(ResultBase<?> biz) {
        this.setCode(biz.getCode());
        this.setMsg(biz.getMsg());
        this.setSuccess(biz.isSuccess());
        return this;
    }

    public void setTemplateMsg(String templateMsg) {
        this.msg = templateMsg;
    }

}
