package com.pavoindus.authentication.autoconfigue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "payroll-results.model-training", ignoreUnknownFields = false)
public class ModelTrainingProperties extends PayrollServiceProperties {

    public ModelTrainingProperties() {
        super();
    }
}
