package com.pavoindus.authentication.autoconfigue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "payroll-results.predictive", ignoreUnknownFields = false)
public class PredictiveProperties extends PayrollServiceProperties {
    public PredictiveProperties() {
        super();
    }
}
