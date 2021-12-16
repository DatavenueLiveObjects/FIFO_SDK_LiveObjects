package com.orange.lo.sdk.rest.apikeys;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.orange.lo.sdk.rest.model.ApiKey;
import com.orange.lo.sdk.rest.model.V0Response;

@ExtendWith(MockitoExtension.class)
class ApiKeysTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<ApiKey> apiKeyResponseEntity;
    @Mock
    private ResponseEntity<V0Response<ApiKey>> apiKeysResponseEntity;
    private ApiKey apiKey;
    private ApiKeys apiKeys;

    @BeforeEach
    void setUp() {
    	apiKey = new ApiKey();
        this.apiKeys = new ApiKeys(() -> restTemplate);
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenGettingDeviceData() {
        String expectedEndpoint = "/v0/apiKeys/apiKeyId";
        when(restTemplate.exchange(eq(expectedEndpoint), eq(HttpMethod.GET), any(), eq(ApiKey.class)))
                .thenReturn(apiKeyResponseEntity);
        when(apiKeyResponseEntity.getBody()).thenReturn(apiKey);

        ApiKey apiKey = apiKeys.getApiKey("apiKeyId");

        assertNotNull(apiKey);
        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint), eq(HttpMethod.GET), any(), eq(ApiKey.class));
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenGettingDevicesData() {
        String expectedEndpoint = "/v0/apiKeys";
        when(restTemplate.exchange(eq(expectedEndpoint),
                eq(HttpMethod.GET),
                any(),
                ArgumentMatchers.<ParameterizedTypeReference<V0Response<ApiKey>>>any())
        ).thenReturn(apiKeysResponseEntity);
        when(apiKeysResponseEntity.getBody()).thenReturn(new V0Response<ApiKey>());

        GetApiKeysFilter getApiKeysFilter = new GetApiKeysFilter();
        List<ApiKey> apikeysList = apiKeys.getApiKeys(getApiKeysFilter);

        assertNotNull(apikeysList);
        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint),
                        eq(HttpMethod.GET), any(),
                        ArgumentMatchers.<ParameterizedTypeReference<V0Response<ApiKey>>>any()
                );
    }
}