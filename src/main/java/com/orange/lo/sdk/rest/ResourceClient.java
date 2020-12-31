/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class ResourceClient {

    private final RestTemplate restTemplate;

    public ResourceClient(RestTemplateFactory restTemplateFactory) {
        this.restTemplate = restTemplateFactory.getRestTemplate();
    }

    protected <T> T get(String uri, Class<T> responseType) {
        ResponseEntity<T> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);
        return exchange.getBody();
    }

    protected <T> List<T> getMany(String uri, ParameterizedTypeReference<List<T>> typeRef) {
        ResponseEntity<List<T>> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, typeRef);
        return exchange.getBody();
    }
    
    protected void delete(String uri) {
    	restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);
	}
    
    protected <T> T create(String uri, T body, Class<T> responseType) {
    	ResponseEntity<T> exchange = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<T>(body), responseType);
		return exchange.getBody();
	}
}