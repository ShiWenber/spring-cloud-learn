package edu.ynu.entity;

import java.io.Serializable;

/**
 * 让服务调用者获知调用的状态
 */
public class CommonResult<type> implements Serializable {
    private Integer code;
    private String message;
    private User result;

    public CommonResult() {

    }

    public CommonResult(Integer code, String message, User result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
