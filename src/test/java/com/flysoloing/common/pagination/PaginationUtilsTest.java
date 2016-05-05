package com.flysoloing.common.pagination;

import org.junit.Before;
import org.junit.Test;

/**
 * @author laitao
 * @since 2016-05-05 16:52:55
 */
public class PaginationUtilsTest {
    private int totalRecord;
    private int pageSize;
    private int indexOffset;
    private int currentIndex;
    private Pager pager;

    @Before
    public void setUp() throws Exception {
        this.totalRecord = 18;
        this.pageSize = 3;
        this.indexOffset = 2;
        this.currentIndex = 2;
        this.pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
    }

    @Test
    public void testPagination() throws Exception {
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
    }

    @Test
    public void testShowPaginationBar() throws Exception {
        System.out.println((PaginationUtils.showPaginationBar(pager)));
    }
}
