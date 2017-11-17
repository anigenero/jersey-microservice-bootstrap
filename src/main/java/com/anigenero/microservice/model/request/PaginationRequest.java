package com.anigenero.microservice.model.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class PaginationRequest implements Serializable {

    public static final Integer DEFAULT_SIZE = 10;

    @QueryParam("page")
    private Integer page;
    @QueryParam("size")
    private Integer size;

    @QueryParam("all")
    private boolean getAll;

    public Integer getPage() {
        if (page != null) {
            return page;
        } else {
            return 0;
        }
    }

    public void setPage(Integer start) {
        this.page = start;
    }

    public Integer getSize() {
        if (size != null) {
            return size;
        } else {
            return DEFAULT_SIZE;
        }
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isGetAll() {
        return getAll;
    }

    public void setGetAll(boolean getAll) {
        this.getAll = getAll;
    }

    public Pageable toPageable() {
        if (this.isGetAll()) {
            return null;
        } else {
            return new PageRequest(getPage(), getSize());
        }
    }

}
