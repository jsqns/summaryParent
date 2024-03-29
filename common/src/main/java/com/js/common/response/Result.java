package com.js.common.response;


import lombok.Data;

@Data
public class Result<T> {
    private Integer status;
    private String message;
    private T data;

    public Result(){
        this.status = ResultConstant.SUCCESS.getStatus();
    }
    public Result(Integer status, String message){
        this.status = status;
        this.message = message;
    }
    public Result(T data){
        this.data = data;
        this.status = ResultConstant.SUCCESS.getStatus();
    }
}
