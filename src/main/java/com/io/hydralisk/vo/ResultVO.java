package com.io.hydralisk.vo;

public class ResultVO {
    private int error;
    private String message;
    private String url;
    private Object data;

    public ResultVO(Object data, String url) {
        this.error = 0;
        this.message = "success";
        this.url = url;
        this.data = data;
    }

    public ResultVO(int error, String message, Object data, String url) {
        this.error = error;
        this.message = message;
        this.url = url;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
