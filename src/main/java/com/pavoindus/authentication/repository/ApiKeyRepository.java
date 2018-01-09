package com.pavoindus.authentication.repository;

import com.pavoindus.authentication.model.ApiKey;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApiKeyRepository extends PagingAndSortingRepository<ApiKey, Long> {

    ApiKey findByApiKey(String apiKey);
    ApiKey findByApplicationName(String applicationName);
    ApiKey findByApiKeyAndApplicationName(String apiKey, String applicationName);
}
