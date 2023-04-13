package com.example.forumBackEnd.pojo.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.executor.ErrorContext;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_SERVICE_UNAVAILABLE;

@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse {
    private int code;
    private String message;
    private Object data;

    public static ErrorContext error() {
        return null;
    }

    public static ErrorContext ok() {
        return null;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 生成仅带代码的无参成功回复
     */
    public static BasicResponse getSuccessResponse(){
        return new BasicResponse(SC_OK, "请求成功", null);
    }

    /**
     * 生成带DTO的回复
     * @param data 返回给前端的DTO
     */
    public static BasicResponse getSuccessResponse(Object data){
        return new BasicResponse(SC_OK, "请求成功", data);
    }

    /**
     * 生成自定义返回信息的带DTO回复
     * @param msg 返回给前端的信息
     * @param data 返回给前端的DTO
     */
    public static BasicResponse getSuccessResponse(String msg, Object data){
        return new BasicResponse(SC_OK, msg, data);
    }

    /**
     * 生成默认错误状态回复
     */
    public static BasicResponse getFailResponse(){
        return new BasicResponse(SC_SERVICE_UNAVAILABLE, "请求发生错误", null);
    }

    /**
     * 生成指定错误信息的错误状态回复
     * @param msg 错误信息
     */
    public static BasicResponse getFailResponse(String msg){
        return new BasicResponse(SC_SERVICE_UNAVAILABLE, msg, null);
    }

    /**
     * 生成指定状态码与错误信息的错误状态回复
     * @param msg 错误信息
     */
    public static BasicResponse getFailResponse(int code, String msg){
        return new BasicResponse(code, msg, null);
    }
}
