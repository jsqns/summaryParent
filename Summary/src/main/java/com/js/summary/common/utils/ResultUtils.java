package com.js.summary.common.utils;


import com.js.summary.common.Result;

public class ResultUtils {
    public static<T> Result<T> createSuccessRes(T data){
        return new Result<>(data);
    }
    public static<T> Result<T> createSuccessRes(){
        return new Result<>();
    }

}
