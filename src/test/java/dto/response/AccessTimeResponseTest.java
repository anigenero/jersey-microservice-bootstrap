package dto.response;

import com.anigenero.microservice.model.response.AccessTimeResponse;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AccessTimeResponseTest {

    @Test
    public void getAccessed() throws Exception {

        AccessTimeResponse<String> accessTimeResponse1 = new AccessTimeResponse<>("test");
        assertThat(accessTimeResponse1.getAccessed()).isNotNull();

        AccessTimeResponse<String> accessTimeResponse2 = new AccessTimeResponse<>();
        assertThat(accessTimeResponse2.getAccessed()).isNotNull();

    }

}