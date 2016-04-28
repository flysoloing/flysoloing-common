package com.flysoloing.common.bean;

/**
 * 返回结果抽象类
 *
 * @author laitao
 * @since 2015-03-18 14:48:00
 */
public abstract class AbstractResult {

    /**
     * 结果编码
     */
    private String code;
    /**
     * 结果信息
     */
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
