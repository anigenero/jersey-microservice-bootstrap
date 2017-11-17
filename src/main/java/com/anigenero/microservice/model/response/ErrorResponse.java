package com.anigenero.microservice.model.response;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponse extends DefaultResponse<Serializable> {

    private final String errorMessage;

    private ArrayList<String> messages;

    public ErrorResponse(Throwable throwable) {

        super(null);

        this.setStatus(Response.Status.INTERNAL_SERVER_ERROR);

        this.errorMessage = throwable.getMessage();

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void addMessage(String message) {

        if (this.messages == null) {
            messages = new ArrayList<>();
        }

        messages.add(message);

    }

    public List<String> getMessages() {
        return messages;
    }

}
