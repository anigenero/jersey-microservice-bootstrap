package com.anigenero.microservice.model.response;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;

public class ValidationErrorResponse extends DefaultResponse<Serializable>  {

    public ValidationErrorResponse(ArrayList<String> violations) {

        super(null);

        this.setWarnings(violations);
        this.setStatus(Response.Status.BAD_REQUEST);

    }

}
