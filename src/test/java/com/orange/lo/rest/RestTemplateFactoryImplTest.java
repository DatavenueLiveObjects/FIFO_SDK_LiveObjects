package com.orange.lo.rest;

import com.orange.lo.LOApiClientParameters;
import org.junit.jupiter.api.Test;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestTemplateFactoryImplTest {

    private static final String API_KEY = "abcDEfgH123I";

    @Test
    void shouldCorrectlyCreateRestTemplate() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();
        RestTemplateFactoryImpl restTemplateFactory = new RestTemplateFactoryImpl(parameters);

        RestTemplate restTemplate = restTemplateFactory.getRestTemplate();

        UriTemplateHandler uriTemplateHandler = restTemplate.getUriTemplateHandler();
        assertTrue(uriTemplateHandler instanceof DefaultUriBuilderFactory);

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        assertEquals(3, interceptors.size());
    }
}