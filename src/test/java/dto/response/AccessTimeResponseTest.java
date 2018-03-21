package dto.response;

import com.anigenero.microservice.model.response.AccessTimeResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AccessTimeResponseTest {

    @Test
    void getAccessed() {

        AccessTimeResponse<String> accessTimeResponse1 = new AccessTimeResponse<>("test");
        assertThat(accessTimeResponse1.getAccessed()).isNotNull();

        AccessTimeResponse<String> accessTimeResponse2 = new AccessTimeResponse<>();
        assertThat(accessTimeResponse2.getAccessed()).isNotNull();

    }

}