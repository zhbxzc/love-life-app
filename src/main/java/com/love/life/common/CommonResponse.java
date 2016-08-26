package com.love.life.common;

import com.alibaba.fastjson.JSONObject;



public class CommonResponse<T> {

//    失败：
//    {"status":"failure","errorCode":10001,"errorMessage":"this is a error","data":[]}
//    成功：【键值对】
//    {"status":"success","errorCode":0,"errorMessage":"","data":{"COLUMN1":"I AM A STRING","COLUMN2":"I AM ALSO A STRING"}}
//    成功：【单值】
//    {"status":"success","errorCode":0,"errorMessage":"","data":["I AM A STRING"]}
//    成功：【数组】
//    {"status":"success","errorCode":0,"errorMessage":"","data":[{"COLUMN1.1":"STRING1.1","COLUMN1.2":"STRING1.2"},{"COLUMN2.1":"STRING1.1","COLUMN2.2":"STRING2.2"}]}


    private int errorCode = 0;
    private String errorMessage = "成功";
    private String status = "success";

    private T data;

    public int geterror_code() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String geterror_message() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        //return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        JSONObject errorJson = new JSONObject();
        errorJson.put("status", status);
        errorJson.put("data", this.data);
        errorJson.put("errorMessage", errorMessage);
        errorJson.put("errorCode", errorCode);
        return errorJson.toString();
    }
}