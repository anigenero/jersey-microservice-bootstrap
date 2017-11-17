package com.anigenero.microservice.resource.mapper;

import com.anigenero.microservice.model.response.ValidationErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        ArrayList<String> warnings = new ArrayList<>();

        // add the constraint violations to the error details
        for (ConstraintViolation constraintViolation : exception.getConstraintViolations()) {
            warnings.add(constraintViolation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
                .entity(new ValidationErrorResponse(warnings)).build();

    }

}
