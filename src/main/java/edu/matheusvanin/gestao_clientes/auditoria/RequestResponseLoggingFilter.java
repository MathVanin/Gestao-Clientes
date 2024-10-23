package edu.matheusvanin.gestao_clientes.auditoria;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private final LogRepository logRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            Log log = requestToLog(wrappedRequest);
            log.setResponseStatus(wrappedResponse.getStatus());
            log.setResponseContent(
                    new String(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding()));

            logRepository.save(log);

            wrappedResponse.copyBodyToResponse();
        }
    }

    private Log requestToLog(ContentCachingRequestWrapper request) throws IOException {
        return Log.builder()
                .body(new String(request.getContentAsByteArray(), request.getCharacterEncoding()))
                .method(request.getMethod())
                .endpoint(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
