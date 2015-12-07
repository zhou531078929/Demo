package com.example.jackiechan.volleydemo;

/**
 * Created by jackiechan on 15/12/7.
 */
public class Data1  extends BaseJsonObject{
    String success;
    String message;
    String statusCode;

    public Data1() {
    }

    public Data1(String tag) {
        this.tag = tag;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
