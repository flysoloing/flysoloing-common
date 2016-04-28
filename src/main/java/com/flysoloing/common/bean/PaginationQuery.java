package com.flysoloing.common.bean;

import com.flysoloing.common.pagination.Pagination;

import java.io.Serializable;

/**
 * 带分页条件查询类
 *
 * @author laitao
 * @since 2015-03-18 15:59:00
 */
public class PaginationQuery<T> extends Query<T> implements Serializable {
    private static final long serialVersionUID = -1440485676534570713L;

    private Pagination pagination;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
