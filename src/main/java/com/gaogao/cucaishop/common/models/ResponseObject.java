package com.gaogao.cucaishop.common.models;

import java.io.Serializable;

public class ResponseObject<T> implements Serializable {
    public static final ResponseObject SUCCESS_WITHOUT_DATA = new ResponseObject(null);
    public static final ResponseObject FAILURE = new ResponseObject(Boolean.FALSE, CommonStrings.RESP_MSG_SERVER_ERROR);
    public static final ResponseObject NOT_FOUND = new ResponseObject(Boolean.FALSE, CommonStrings.RESP_MSG_NOT_FOUND);

    private boolean success;

    private String message;

    private T data;

    public ResponseObject() {
        this.setSuccess(true);
        this.setMessage(CommonStrings.RESP_MSG_SUCCESS);
    }

    public ResponseObject(T data) {
        this.success = true;
        this.message = "Success";
        this.data = data;
    }

    public ResponseObject(boolean success, String message) {
        this(success, message, null);
    }

    public ResponseObject(boolean success, String message, T data) {
        this.setSuccess(success);
        this.setMessage(message);
        this.setData(data);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage(String format, String... params) {
        if (params != null && params.length > 0) {
            this.message = String.format(format, (Object[]) params);
        }
    }

    public void setFailMessage(String format, String... params) {
        this.success = false;
        if (params != null && params.length > 0) {
            this.message = String.format(format, (Object[]) params);
        } else {
            this.message = format;
        }
    }

    public static ResponseObject failResponse(String message) {
        return new ResponseObject(false, message);
    }
}
