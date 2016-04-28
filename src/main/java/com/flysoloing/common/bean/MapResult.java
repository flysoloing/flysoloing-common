package com.flysoloing.common.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Map类型返回结果类
 *
 * @author laitao
 * @since 2015-03-18 15:03:00
 */
public class MapResult<T> extends AbstractResult implements Serializable {
    private static final long serialVersionUID = -6735175977188647965L;

    private Map<String, T> map = new HashMap<String, T>();

    public Map<String, T> getMap() {
        return map;
    }

    public void setMap(Map<String, T> map) {
        this.map = map;
    }

    public T put(String key, T value) {
        return this.map.put(key, value);
    }

    public T remove(String key) {
        return this.map.remove(key);
    }

    public T get(String key) {
        return this.map.get(key);
    }
}
