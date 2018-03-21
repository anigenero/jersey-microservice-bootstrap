package dto.response;

import com.anigenero.microservice.model.response.DefaultResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DefaultResponseTest {

    @Test
    void testDefaultResponse() {

        ArrayList<String> warnings = new ArrayList<>(Arrays.asList("Warning 1", "Warning 2"));

        DefaultResponse<String> response = new DefaultResponse<>("test");
        response.setWarnings(warnings);

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.getWarnings()).isEqualTo(warnings);
        assertThat(response.getData()).isEqualTo("test");

    }

    @Test
    void testDefaultResponseWithSetData() {

        DefaultResponse<String> response = new DefaultResponse<>();
        response.setData("test");

        assertThat(response.getData()).isEqualTo("test");

    }

    @Test
    void testDefaultResponseNullData() {

        DefaultResponse<String> response = new DefaultResponse<>();
        assertThat(response.getData()).isNull();

    }

}