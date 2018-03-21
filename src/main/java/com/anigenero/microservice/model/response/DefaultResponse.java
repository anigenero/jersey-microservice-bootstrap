package com.anigenero.microservice.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;

@ApiModel(description = "Wraps a default response from the server")
public class DefaultResponse<T extends Serializable> implements Serializable {

    private T data;
    private Response.Status status = Response.Status.OK;
    private ArrayList<String> warnings;

    public DefaultResponse() {
        // default constructor for using #setData()
    }

    public DefaultResponse(T data) {
        this.data = data;
    }

    @ApiModelProperty(required = true, value = "The result data")
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @ApiModelProperty(value = "The response status")
    public Integer getStatus() {
        return status.getStatusCode();
    }

    @SuppressWarnings("WeakerAccess")
    public void setStatus(Response.Status status) {
        this.status = status;
    }

    @ApiModelProperty(value = "Warning messages that were appended during the request")
    public ArrayList<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(ArrayList<String> warnings) {
        this.warnings = warnings;
    }

}
