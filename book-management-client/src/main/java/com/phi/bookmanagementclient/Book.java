package com.phi.bookmanagementclient;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Book {
    private String id;
    private String name;
    private String pageCount;

    public Book() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("pageCount", pageCount)
                .toString();
    }
}
