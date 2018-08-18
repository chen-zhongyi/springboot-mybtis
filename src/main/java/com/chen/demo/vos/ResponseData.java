package com.chen.demo.vos;

import java.io.Serializable;

public class ResponseData implements Serializable{

    private int code;
    private String message;
    private long systemTime = System.currentTimeMillis();

    private Data data;

    public static ResponseData success(){
        ResponseData responseData = new ResponseData();
        responseData.setCode(ResponseStatus.SUCCESS.code());
        responseData.setMessage(ResponseStatus.SUCCESS.description());
        responseData.setData(null);
        return responseData;
    }

    public static ResponseData success(Data data){
        ResponseData responseData = new ResponseData();
        responseData.setCode(ResponseStatus.SUCCESS.code());
        responseData.setMessage(ResponseStatus.SUCCESS.description());
        responseData.setData(data);
        return responseData;
    }

    public static ResponseData failed(ResponseStatus.Status status){
        ResponseData responseData = new ResponseData();
        responseData.setCode(status.code());
        responseData.setMessage(status.description());
        responseData.setData(null);
        return responseData;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(long systemTime) {
        this.systemTime = systemTime;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
