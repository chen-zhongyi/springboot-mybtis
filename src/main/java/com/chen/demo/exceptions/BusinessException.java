package com.chen.demo.exceptions;

import com.chen.demo.vos.ResponseStatus;

public class BusinessException extends Exception{

    private ResponseStatus.Status status;

    public BusinessException(ResponseStatus.Status status){
        super(status.description());
        this.status = status;
    }

    public BusinessException(String description){
        super(description);
        this.status = new ResponseStatus.Status(1001, description);
    }

    public ResponseStatus.Status status(){
        return this.status;
    }
}
