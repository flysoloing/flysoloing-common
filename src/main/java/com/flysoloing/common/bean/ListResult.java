package com.flysoloing.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * List类型返回结果类
 *
 * @author laitao
 * @since 2015-03-18 14:56:00
 */
public class ListResult<T> extends AbstractResult implements Serializable {
    private static final long serialVersionUID = -7450177725900417295L;

    private List<T> list = new ArrayList<T>();

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean add(T value) {
        return this.list.add(value);
    }
}
