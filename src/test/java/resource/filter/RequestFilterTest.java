package resource.filter;

import com.anigenero.microservice.resource.filter.RequestFilter;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

public class RequestFilterTest {

    private RequestFilter requestFilter;

    @Before
    public void setUp() throws Exception {
        this.requestFilter = new RequestFilter();
    }

    @Test
    public void init() throws Exception {

        FilterConfig filterConfig = mock(FilterConfig.class);

        this.requestFilter.init(filterConfig);

    }

    @Test
    public void doFilter() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getRequestURI()).thenReturn("/test");

        this.requestFilter.doFilter(request, response, filterChain);

        verify(request).getRequestURI();
        verify(filterChain, times(1)).doFilter(request, response);

    }

    @Test
    public void doFilterWithError() throws Exception {

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
    public void doFilterWithReallyLongRequestTime() throws Exception {

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
    public void doFilterWithLongRequestTime() throws Exception {

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
    public void destroy() throws Exception {
        this.requestFilter.destroy();
    }

}