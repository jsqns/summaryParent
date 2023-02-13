package com.js.common.response.reqUtils;


import com.js.common.response.Result;

public class ResultUtils {
    public static<T> Result<T> createSuccessRes(T data){
        return new Result<>(data);
    }
    public static<T> Result<T> createSuccessRes(){
        return new Result<>();
    }

}
