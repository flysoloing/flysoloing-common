package com.flysoloing.common.pagination;

/**
 * 分页包裹类<br>
 * User: laitao<br>
 * Date: 14-11-21<br>
 * Time: 下午4:50<br>
 */
public class Pager {

    private int totalRecord;//总记录条数
    private int pageSize;//每页记录数
    private int currentIndex;//当前页码
    private int indexOffset;//页面偏移量，例如：为2时，当前页面的前2和后2都显示

    private int totalPage;//总页数

    private boolean isPrevious;//	是否展示上一页
    private boolean isNext;//是否展示下一页
    private int previousIndex;//上一页码
    private int nextIndex;//	下一页码

    private boolean isFirst;//是否展示第一页页码
    private boolean isLast;//是否展示最后页页码
    private int firstIndex;//第一页页码
    private int lastIndex;//最后页页码

    private boolean isPreviousAbbr;//是否展示前缩略号
    private boolean isNextAbbr;//是否展示后缩略号

    private int fromIndex;//中间起始页码
    private int toIndex;//中间结束页码

    public Pager(){}

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getIndexOffset() {
        return indexOffset;
    }

    public void setIndexOffset(int indexOffset) {
        this.indexOffset = indexOffset;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isPrevious() {
        return isPrevious;
    }

    public void setPrevious(boolean isPrevious) {
        this.isPrevious = isPrevious;
    }

    public boolean isNext() {
        return isNext;
    }

    public void setNext(boolean isNext) {
        this.isNext = isNext;
    }

    public int getPreviousIndex() {
        return previousIndex;
    }

    public void setPreviousIndex(int previousIndex) {
        this.previousIndex = previousIndex;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean isLast) {
        this.isLast = isLast;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public boolean isPreviousAbbr() {
        return isPreviousAbbr;
    }

    public void setPreviousAbbr(boolean isPreviousAbbr) {
        this.isPreviousAbbr = isPreviousAbbr;
    }

    public boolean isNextAbbr() {
        return isNextAbbr;
    }

    public void setNextAbbr(boolean isNextAbbr) {
        this.isNextAbbr = isNextAbbr;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }
}
