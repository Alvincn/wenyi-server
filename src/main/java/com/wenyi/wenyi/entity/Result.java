package com.wenyi.wenyi.entity;

import lombok.Data;

/**
 * @author swb
 * @version 1.0
 * @date 2024/03/11 21:41
 **/
@Data
public class Result {
    enum errorCode {

    }
    private int code;    // 返回结果编码
    private String message; // 返回信息
    private Object data;         // 返回数据

    private Result(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    private Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result success(Object data, String message) {
        return new Result(200, data, message);
    }

    public static Result success(Object data) {
        return new Result(200, data, "请求成功");
    }

    public static Result success() {
        return new Result(200, null, "请求成功");
    }

    public static Result fail(int code, String message) {
        return new Result(code, message);
    }

    public static Result fail() {
        return new Result(205, "操作失败");
    }
}
