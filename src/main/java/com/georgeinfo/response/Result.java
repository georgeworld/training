package com.georgeinfo.response;


/**
 * API方法通用响应bean，对外提供的响应容器类
 * Date: 2018/7/17 20:45
 *
 * @author George
 */
public class Result<T> {
    /**
     * 查询结果状态码：查询成功=0, 查询失败 = 400, 其他值，等同于失败
     */
    private int code;
    /**
     * 查询执行的结果描述，比如因为XX原因而查询失败
     */
    private String msg;
    /**
     * 查询成功时，向查询方返回的结果值对象
     */
    private T data;
    /**
     * 更多数据
     */
    private Object detail;

    public Result() {
    }

    public Result(ResultTypeEnum result) {
        this.code = result.getCode();
    }

    public Result(ResultTypeEnum result, String msg, T data) {
        this.code = result.getCode();
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> createSuccess() {
        Result<T> response = new Result<T>(ResultTypeEnum.SUCCESS);
        return response;
    }

    public static <T> Result<T> createSuccess(T body) {
        Result<T> response = new Result<T>(ResultTypeEnum.SUCCESS);
        response.setData(body);
        return response;
    }

    public static <T> Result<T> createFailureResponse() {
        Result<T> response = new Result<T>(ResultTypeEnum.FAILURE);
        return response;
    }

    public static <T> Result<T> createEmptyResponse() {
        Result<T> response = new Result<T>(ResultTypeEnum.EMPTY);
        return response;
    }

    public boolean isSuccess() {
        return this.code == ResultTypeEnum.SUCCESS.getCode();
    }
    public Result<T> success() {
        this.code = ResultTypeEnum.SUCCESS.getCode();
        return this;
    }

    public Result<T> failure() {
        this.code = ResultTypeEnum.FAILURE.getCode();
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public Object getDetail() {
        return detail;
    }

    public Result<T> setDetail(Object detail) {
        this.detail = detail;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Result<?> that = (Result<?>) o;

        if (code != that.code) {
            return false;
        }
        if (msg != null ? !msg.equals(that.msg) : that.msg != null) {
            return false;
        }
        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EchoResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }



}
