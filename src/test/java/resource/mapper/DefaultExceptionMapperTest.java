package resource.mapper;

import com.anigenero.microservice.model.response.ErrorResponse;
import com.anigenero.microservice.resource.mapper.DefaultExceptionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DefaultExceptionMapperTest {

    private DefaultExceptionMapper defaultExceptionMapper;

    @BeforeEach
    void setUp() {
        this.defaultExceptionMapper = new DefaultExceptionMapper();
    }

    @Test
    void toResponse() {

        final String message = "Error thrown";

        Response response = defaultExceptionMapper.toResponse(new Exception(message));

        // Note: security practices tell us we should never return a 500 error, but rather a 200 with error details
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.getMediaType().toString()).isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(response.getEntity()).isInstanceOf(ErrorResponse.class);

        ErrorResponse errorDetails = (ErrorResponse) response.getEntity();

        assertThat(errorDetails.getErrorMessage()).isEqualTo(message);

    }

}