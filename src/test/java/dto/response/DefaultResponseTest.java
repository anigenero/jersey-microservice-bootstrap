package dto.response;

import com.anigenero.microservice.model.response.DefaultResponse;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DefaultResponseTest {

    @Test
    public void testDefaultResponse() throws Exception {

        ArrayList<String> warnings = new ArrayList<>(Arrays.asList("Warning 1", "Warning 2"));

        DefaultResponse<String> response = new DefaultResponse<>("test");
        response.setWarnings(warnings);

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.getWarnings()).isEqualTo(warnings);
        assertThat(response.getData()).isEqualTo("test");

    }

    @Test
    public void testDefaultResponseWithSetData() throws Exception {

        DefaultResponse<String> response = new DefaultResponse<>();
        response.setData("test");

        assertThat(response.getData()).isEqualTo("test");

    }

    @Test
    public void testDefaultResponseNullData() throws Exception {

        DefaultResponse<String> response = new DefaultResponse<>();
        assertThat(response.getData()).isNull();

    }

}