package com.example.score.utils;

import com.example.score.common.Result;

import java.io.Serializable;

public class ResultUtils implements Serializable {

    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result success(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }
}
