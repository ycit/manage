package com.ycit.controller;

import com.ycit.bean.base.ApiResponse;

import java.util.List;

/**
 * 控制层基类
 *
 * @author xlch
 * @Date 2018-03-23 13:19
 */
public class BaseController <T>{

    public ApiResponse<T> success(List<T> results, int total) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setResult(results);
        response.setCode(200);
        response.setTotal(total);
        return response;
    }

    public <T> ApiResponse<T> error(Integer code, String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        return apiResponse;
    }

}
