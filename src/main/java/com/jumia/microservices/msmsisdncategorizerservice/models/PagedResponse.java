package com.jumia.microservices.msmsisdncategorizerservice.models;

import java.util.List;

public class PagedResponse {
    private int numberOfPages;
    private int page;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private long totalNumberOfElements;
    private List<PhoneNumberResponseObject> data;

    public PagedResponse() {
    }

    public PagedResponse(int numberOfPages, int page, boolean hasNextPage, boolean hasPreviousPage, long totalNumberOfElements, List<PhoneNumberResponseObject> data) {
        this.numberOfPages = numberOfPages;
        this.page = page;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
        this.totalNumberOfElements = totalNumberOfElements;
        this.data = data;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public long getTotalNumberOfElements() {
        return totalNumberOfElements;
    }

    public void setTotalNumberOfElements(long totalNumberOfElements) {
        this.totalNumberOfElements = totalNumberOfElements;
    }

    public List<PhoneNumberResponseObject> getData() {
        return data;
    }

    public void setData(List<PhoneNumberResponseObject> data) {
        this.data = data;
    }
}
