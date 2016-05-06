package com.flysoloing.common.pagination;

/**
 * 分页工具类
 *
 * @author laitao
 * @since 2014-11-21 16:50:00
 */
public class PaginationUtils {

    /**
     * 根据分页条件得到分页对象
     *
     * @param totalRecord 总记录条数
     * @param pageSize 每页记录数
     * @param currentIndex 当前页码
     * @param indexOffset 页面偏移量，例如：为2时，当前页面的前2和后2都显示
     * @return 分页对象
     */
    public static Pager pagination(int totalRecord, int pageSize, int currentIndex, int indexOffset) {
        int totalPage = (totalRecord + pageSize - 1) / pageSize;
        if (totalPage <=0 )
            return null;
        Pager pager = new Pager();
        pager.setCurrentIndex(currentIndex);
        pager.setPageSize(pageSize);
        pager.setTotalPage(totalPage);
        pager.setTotalRecord(totalRecord);
        pager.setIndexOffset(indexOffset);

        if (pageSize >= totalRecord) {
            pager.setFromIndex(1);
            pager.setToIndex(1);
            return pager;
        }
        if (currentIndex >= totalPage) {
            currentIndex = totalPage;
            pager.setCurrentIndex(totalPage);
        }

        //是否展示上一页，如果等于首页
        if (currentIndex != 1) {
            pager.setPrevious(true);
        }
        //是否展示下一页，如果等于末页
        if (currentIndex != totalPage) {
            pager.setNext(true);
        }
        //是否展示前缩略号
        if (currentIndex - 1 > indexOffset + 1) {
            pager.setPreviousAbbr(true);
        }
        //是否展示后缩略号
        if (totalPage - currentIndex > indexOffset + 1) {
            pager.setNextAbbr(true);
        }
        //设置上一页和下一页页码
        if (currentIndex - 1 > 0) {
            pager.setPreviousIndex(currentIndex - 1);
            pager.setNextIndex(currentIndex + 1);
        }
        //设置上一页和下一页页码
        if (totalPage - currentIndex > 0) {
            pager.setPreviousIndex(currentIndex - 1);
            pager.setNextIndex(currentIndex + 1);
        }
        //中间起始页码
        pager.setFromIndex(currentIndex - indexOffset > 1 ? currentIndex - indexOffset : 1);
        //中间结束页码
        pager.setToIndex(currentIndex + indexOffset > totalPage ? totalPage : currentIndex + indexOffset);

        //是否展示第一页页码
        if (pager.getFromIndex() == 1) {
            pager.setFirst(false);
        } else {
            pager.setFirst(true);
            pager.setFirstIndex(1);
        }
        //是否展示最后页页码
        if (pager.getToIndex() == totalPage) {
            pager.setLast(false);
        } else {
            pager.setLast(true);
            pager.setLastIndex(totalPage);
        }
        return pager;
    }

    public static String showPaginationBar(Pager pager) {
        if (pager == null) {
            return  "当前分页对象为null，记录数为0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("总记录数：").append(pager.getTotalRecord())
                .append("总页数：").append(pager.getTotalPage())
                .append("-------------->>>>>>");
        if (pager.isPrevious()) {
            sb.append("[上一页").append(pager.getPreviousIndex()).append("] ");
        }
        if (pager.isFirst()) {
            sb.append(pager.getFirstIndex()+" ");
        }
        if (pager.isPreviousAbbr()) {
            sb.append("... ");
        }
        for (int i=pager.getFromIndex(); i<=pager.getToIndex(); i++) {
            if (i == pager.getCurrentIndex()) {
                sb.append("[").append(i).append("] ");
            } else {
                sb.append(i+" ");
            }
        }
        if (pager.isNextAbbr()) {
            sb.append("... ");
        }
        if (pager.isLast()) {
            sb.append(pager.getLastIndex() + " ");
        }
        if (pager.isNext()) {
            sb.append("[下一页").append(pager.getNextIndex()).append("]");
        }

        return sb.toString();
    }

    public static void main(String[]args) throws Exception {
        int totalRecord = 3;
        int pageSize = 7;
        int indexOffset = 2;

        int currentIndex = 1;
        Pager pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 13;
        pageSize = 7;
        indexOffset = 2;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 19;
        pageSize = 7;
        indexOffset = 2;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 23;
        pageSize = 7;
        indexOffset = 2;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 33;
        pageSize = 7;
        indexOffset = 2;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 41;
        pageSize = 7;
        indexOffset = 2;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 43;
        pageSize = 7;
        indexOffset = 2;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 55;
        pageSize = 7;
        indexOffset = 2;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 63;
        pageSize = 7;
        indexOffset = 2;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 69;
        pageSize = 7;
        indexOffset = 3;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 11;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 12;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 73;
        pageSize = 7;
        indexOffset = 3;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 11;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 12;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 79;
        pageSize = 7;
        indexOffset = 3;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 11;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 12;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 13;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        //
        totalRecord = 88;
        pageSize = 7;
        indexOffset = 3;

        currentIndex = 1;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 2;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 3;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 4;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 5;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 6;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 7;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 8;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 9;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 10;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 11;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 12;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 13;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 17;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 18;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

        currentIndex = 19;
        pager = PaginationUtils.pagination(totalRecord, pageSize, currentIndex, indexOffset);
        System.out.println(PaginationUtils.showPaginationBar(pager));

    }
}
