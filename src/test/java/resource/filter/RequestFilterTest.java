package resource.filter;

import com.anigenero.microservice.resource.filter.RequestFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

class RequestFilterTest {

    private RequestFilter requestFilter;

    @BeforeEach
    void setUp() {
        this.requestFilter = new RequestFilter();
    }

    @Test
    void init() throws Exception {

        FilterConfig filterConfig = mock(FilterConfig.class);

        this.requestFilter.init(filterConfig);

    }

    @Test
    void doFilter() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getRequestURI()).thenReturn("/test");

        this.requestFilter.doFilter(request, response, filterChain);

        verify(request).getRequestURI();
        verify(filterChain, times(1)).doFilter(request, response);

    }

    @Test
    void doFilterWithError() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getRequestURI()).thenReturn("/test");
        doThrow(new ServletException()).when(filterChain).doFilter(request, response);

        this.requestFilter.doFilter(request, response, filterChain);

        verify(request).getRequestURI();
        verify(filterChain, times(1)).doFilter(request, response);

    }

    @Test
    void doFilterWithReallyLongRequestTime() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getRequestURI()).thenReturn("/test");
        doAnswer(invocation -> {
            TimeUnit.SECONDS.sleep(6);
            return null;
        }).when(filterChain).doFilter(request, response);

        this.requestFilter.doFilter(request, response, filterChain);

        verify(request).getRequestURI();
        verify(filterChain, times(1)).doFilter(request, response);

    }

    @Test
    void doFilterWithLongRequestTime() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getRequestURI()).thenReturn("/test");
        doAnswer(invocation -> {
            TimeUnit.SECONDS.sleep(2);
            return null;
        }).when(filterChain).doFilter(request, response);

        this.requestFilter.doFilter(request, response, filterChain);

        verify(request).getRequestURI();
        verify(filterChain, times(1)).doFilter(request, response);

    }

    @Test
    void destroy() {
        this.requestFilter.destroy();
    }

}