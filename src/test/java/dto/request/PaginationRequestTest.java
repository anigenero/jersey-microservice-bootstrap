package dto.request;

import com.anigenero.microservice.model.request.PaginationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

class PaginationRequestTest {

    @Test
    void toPageable() {

        PaginationRequest request = new PaginationRequest();
        request.setSize(20);
        request.setPage(1);

        Pageable pageable = request.toPageable();

        assertThat(pageable.getPageSize()).isEqualTo(20);
        assertThat(pageable.getPageNumber()).isEqualTo(1);

    }

    @Test
    void isGetAll() {

        PaginationRequest request = new PaginationRequest();
        request.setGetAll(true);

        Pageable pageable = request.toPageable();

        assertThat(pageable).isNull();

    }

    @Test
    void defaultSize() {

        PaginationRequest request = new PaginationRequest();
        request.setSize(null);

        assertThat(request.getSize()).isEqualTo(10);

    }

    @Test
    void defaultPage() {

        PaginationRequest request = new PaginationRequest();
        request.setPage(null);

        assertThat(request.getPage()).isEqualTo(0);

    }

}