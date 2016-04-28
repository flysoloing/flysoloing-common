package com.flysoloing.common.bean;

import java.io.Serializable;

/**
 * 通用返回结果类
 *
 * @author laitao
 * @since 2015-03-18 14:54:00
 */
public class GenericResult<T> extends AbstractResult implements Serializable {
    private static final long serialVersionUID = -1342094832750370935L;

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
