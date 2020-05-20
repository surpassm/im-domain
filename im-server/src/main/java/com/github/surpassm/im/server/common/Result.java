package com.github.surpassm.im.server.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 前端JSON返回格式,自定义响应格式
 *
 * @author mc
 * version 1.0
 * date 2018/10/30 12:52
 * description
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    /**
     * 响应业务状态
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应中的数据
     */
    private T data;


    public Result(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Long total, List<T> rows) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMsg();
        this.data = (T) new PageData(total,rows);

    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> ok(Long total, List<T> rows) {
        return new Result<>(total, rows);
    }

    public static Result ok() {
        return new Result<>("");
    }

    public static Result fail() {
        return new Result(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMsg());
    }

    public static Result fail(String msg) {
        return new Result(ResultCode.ERROR.getCode(), msg);
    }

    public static Result fail(Integer code, String msg) {
        return new Result(code, msg);
    }


    public static Result fail(Object data) {
        return new Result<>(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMsg(), data);
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Getter
    @Setter
    public class PageData<T>{
        /**
         * 分页数据
         */
        private Long total;
        /**
         * 分页返回
         */
        private List<T> rows;

        PageData(Long total, List<T> rows){
            this.total = total;
            this.rows = rows;
        }
    }

}
