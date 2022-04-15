package com.shaw.sanos.api.utils;


import com.shaw.sanos.transport.entity.Response;

/**
 * @author shaw
 * @date 2022/4/11
 */
public class ResponseUtil {

    public static <T> Response returnSuccess(T data) {
        Response<T> result = buildResponse();
        result.setData(data);
        return result;
    }

    public static Response returnSuccess() {
        return buildResponse();
    }

    private static <T> Response<T> buildResponse() {
        Response<T> response = new Response<>();
        response.setCode(0);
        response.setSuccess(true);
        response.setMessage("success");
        response.setTime(System.currentTimeMillis());
        return response;
    }

}
