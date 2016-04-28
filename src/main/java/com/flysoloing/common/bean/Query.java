package com.flysoloing.common.bean;

import java.io.Serializable;

/**
 * 通用查询类
 *
 * @author laitao
 * @since 2015-03-18 15:57:00
 */
public class Query<T> implements Serializable {
    private static final long serialVersionUID = 4526451054574810264L;

    private T query;

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }
}
