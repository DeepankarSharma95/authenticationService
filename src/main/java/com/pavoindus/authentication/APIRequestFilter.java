package com.pavoindus.authentication;

import com.pavoindus.authentication.context.ApplicationContext;
import com.pavoindus.authentication.model.ApiKey;
import com.pavoindus.authentication.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class APIRequestFilter implements Filter {

    private static final String PAYROLL_RESULTS_API_KEY_HEADER = "X-Payroll-Results-API-Key";
    private static final String API_KEY_HEADER = "X-Payroll-Results-Auth-API-Key";
    private static final String APPLICATION_NAME_HEADER = "X-Payroll-Results-Application";
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String apiKey;
        String serviceSpecificApiKey = request.getHeader(API_KEY_HEADER);
        if(serviceSpecificApiKey == null) {
            apiKey = request.getHeader(PAYROLL_RESULTS_API_KEY_HEADER);
        } else {
            apiKey = serviceSpecificApiKey;
        }
        String applicationName = request.getHeader(APPLICATION_NAME_HEADER);
        ApiKey apiKeyObject = null;
        if(apiKey != null && applicationName != null) {
            apiKeyObject = apiKeyRepository.findByApiKeyAndApplicationName(apiKey, applicationName);
        }
        if(apiKeyObject != null) {
            ApplicationContext.create();
            ApplicationContext.get().setApiKey(apiKeyObject);
            HttpServletResponse response = ((HttpServletResponse) servletResponse);
            response.setHeader("Access-Control-Allow-Origin", "*");
            filterChain.doFilter(servletRequest, servletResponse);
            ApplicationContext.destroy();
        } else {
            HttpServletResponse response = ((HttpServletResponse) servletResponse);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request");
        }
    }

    @Override
    public void destroy() {
    }
}
