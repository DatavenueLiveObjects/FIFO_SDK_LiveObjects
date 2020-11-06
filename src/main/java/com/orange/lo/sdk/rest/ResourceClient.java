/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public abstract class ResourceClient {

    private final RestTemplate restTemplate;

    public ResourceClient(RestTemplateFactory restTemplateFactory) {
        this.restTemplate = restTemplateFactory.getRestTemplate();
    }

    protected <T> T get(String uri, Class<T> responseType) {
        ResponseEntity<T> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);
        return exchange.getBody();
    }

    protected <T> List<T> getMany(String uri) {
        ParameterizedTypeReference<List<T>> parameterizedTypeReference = new ParameterizedTypeReference<List<T>>() {
        };
        ResponseEntity<List<T>> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, parameterizedTypeReference);
        return exchange.getBody();
    }
}