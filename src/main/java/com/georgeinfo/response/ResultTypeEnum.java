package com.georgeinfo.response;

/**
 * 响应码枚举定义
 * Date: 2019/1/16 11:34
 *
 * @author George
 */
public enum ResultTypeEnum {

    SUCCESS(0), FAILURE(-1), EMPTY(-2);
    private final int code;

    private ResultTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
