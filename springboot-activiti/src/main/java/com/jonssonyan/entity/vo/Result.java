package com.jonssonyan.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        return new Result(1, "成功", data);
    }

    public static Result fail() {
        return new Result(0, "失败", null);
    }

    public static Result fail(String msg) {
        return fail(msg, null);
    }

    public static Result fail(String msg, Object data) {
        return new Result(0, msg, data);
    }
}
