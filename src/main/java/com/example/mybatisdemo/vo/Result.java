package com.example.mybatisdemo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName BaseResultVO
 * @Description 返回实体
 * @Author jianshengfei
 * @Date 2021/10/30 23:30
 */
@Data
public class Result<T> {

    @ApiModelProperty("响应码")
    private Integer code;

    @ApiModelProperty("响应消息")
    private String message;

    @ApiModelProperty("响应数据")
    private Object data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result(200, "响应成功", null);
    }

    public static Result success(String message) {
        return new Result(200, message, null);
    }

    public static Result success(Object data) {
        return new Result(200, "响应成功", data);
    }

    public static Result success(String message, Object data) {
        return new Result(200, message, data);
    }


}
