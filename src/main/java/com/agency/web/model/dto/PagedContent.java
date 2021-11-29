package com.agency.web.model.dto;

import java.util.List;

public class PagedContent<T> {

    private List<T> contentList;
    private int totalPageNumber;

    public List<T> getContentList() {
        return contentList;
    }

    public void setContentList(List<T> contentList) {
        this.contentList = contentList;
    }

    public int getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(int totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    @Override
    public String toString() {
        return "PagedContent{" +
                "contentList=" + contentList +
                ", totalPageNumber=" + totalPageNumber +
                '}';
    }
}
