package com.io.hydralisk.vo;

public class RegisterVO {
    private Object data;
    private int error;
    private String message;
    private String url;


    public RegisterVO(int error, String message, Object data, String url) {
        this.data = data;
        this.error = error;
        this.message = message;
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
