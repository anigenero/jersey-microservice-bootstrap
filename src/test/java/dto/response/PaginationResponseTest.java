package dto.response;

import com.anigenero.microservice.model.request.PaginationRequest;
import com.anigenero.microservice.model.response.PaginationResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaginationResponseTest {

    @Test
    void testPaginationResponse() {

        final ArrayList<String> result = new ArrayList<>(Arrays.asList("1", "2"));

        PaginationRequest request = new PaginationRequest();
        request.setPage(1);
        request.setSize(20);

        PaginationResponse<ArrayList<String>, String> response = new PaginationResponse<>(result, request);

        assertThat(response.getPage()).isEqualTo(request.getPage());
        assertThat(response.getSize()).isEqualTo(result.size());

    }

    @Test
    void testPaginationResponseNullData() {

        PaginationRequest request = new PaginationRequest();
        request.setPage(1);
        request.setSize(20);

        PaginationResponse<ArrayList<String>, String> response = new PaginationResponse<>(null, request);

        assertThat(response.getPage()).isEqualTo(1);
        assertThat(response.getSize()).isEqualTo(0);

    }

    @Test
    void testPaginationResponseNoRequest() {

        final ArrayList<String> result = new ArrayList<>(Arrays.asList("1", "2"));

        PaginationResponse<ArrayList<String>, String> response = new PaginationResponse<>(result, null);

        assertThat(response.getPage()).isEqualTo(0);
        assertThat(response.getSize()).isEqualTo(result.size());

    }

    @Test
    void testPaginationResponseNullRequestPage() {

        final ArrayList<String> result = new ArrayList<>(Arrays.asList("1", "2"));

        PaginationRequest request = new PaginationRequest();
        request.setPage(null);
        request.setSize(20);

        PaginationResponse<ArrayList<String>, String> response = new PaginationResponse<>(result, request);

        assertThat(response.getPage()).isEqualTo(0);
        assertThat(response.getSize()).isEqualTo(result.size());

    }

}