package com.anigenero.microservice.model.response;

import com.anigenero.microservice.model.request.PaginationRequest;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Collection;

public class PaginationResponse<V extends Collection<T> & Serializable, T> extends AccessTimeResponse<V> {

    private PaginationRequest request;

    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private Link next;
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private Link previous;

    /**
     * Creates a pagination response for the specified collection
     *
     * @param data    V - the response data collection
     * @param request {@link PaginationRequest}
     */
    public PaginationResponse(V data, PaginationRequest request) {
        super(data);
        this.request = request;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    public Link getPrevious() {
        return previous;
    }

    public void setPrevious(Link previous) {
        this.previous = previous;
    }

    /**
     * Gets the size of the response
     *
     * @return {@link Integer}
     */
    public Integer getSize() {
        if (this.getData() == null) {
            return 0;
        } else {
            return this.getData().size();
        }
    }

    /**
     * Gets the start page
     *
     * @return {@link Integer}
     */
    public Integer getPage() {
        if (this.request != null) {
            return this.request.getPage();
        } else {
            return 0;
        }
    }

}
