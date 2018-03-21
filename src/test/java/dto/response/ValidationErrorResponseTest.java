package dto.response;

import com.anigenero.microservice.model.response.ValidationErrorResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ValidationErrorResponseTest {

    @Test
    void test() {

        final ArrayList<String> violations = new ArrayList<>();
        violations.add("Test validation error");

        final ValidationErrorResponse response = new ValidationErrorResponse(violations);

        assertThat(response.getWarnings()).isEqualTo(violations);
        assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());

    }

}