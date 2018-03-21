package dto.response;

import com.anigenero.microservice.model.response.ErrorResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ErrorResponseTest {

    @Test
    void testErrorResponse() {

        final String errorMessage = "message";

        final String message1 = "foo";
        final String message2 = "bar";

        ErrorResponse errorResponse = new ErrorResponse(new Exception(errorMessage));
        errorResponse.addMessage(message1);
        errorResponse.addMessage(message2);

        assertThat(errorResponse.getErrorMessage()).isEqualTo(errorMessage);

        assertThat(errorResponse.getMessages()).isNotNull();
        assertThat(errorResponse.getMessages().size()).isEqualTo(2);
        assertThat(errorResponse.getMessages().get(0)).isEqualTo(message1);
        assertThat(errorResponse.getMessages().get(1)).isEqualTo(message2);

    }

}