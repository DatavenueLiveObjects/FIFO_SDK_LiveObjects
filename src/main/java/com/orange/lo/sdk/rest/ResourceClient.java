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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.orange.lo.sdk.rest.model.V0Response;

public abstract class ResourceClient {

    private final RestTemplate restTemplate;

    public ResourceClient(RestTemplateFactory restTemplateFactory) {
        this.restTemplate = restTemplateFactory.getRestTemplate();
    }

    protected <T> T get(String uri, Class<T> responseType) {
        ResponseEntity<T> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);
        return exchange.getBody();
    }

    protected <T> T get(String uri, ParameterizedTypeReference<T> typeRef) {
        ResponseEntity<T> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, typeRef);
        return exchange.getBody();
    }

    protected <T> List<T> getMany(String uri, ParameterizedTypeReference<List<T>> typeRef) {
        ResponseEntity<List<T>> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, typeRef);
        return exchange.getBody();
    }

    protected <T> List<T> getV0Many(String uri, ParameterizedTypeReference<V0Response<T>> typeRef) {
        ResponseEntity<V0Response<T>> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, typeRef);
        return exchange.getBody().getData();
    }

    protected void delete(String uri) {
    	restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);
	}

    protected <T, S> S create(String uri, T body, Class<S> responseType) {
        HttpEntity<T> requestEntity = gettHttpEntity(body);
        ResponseEntity<S> exchange = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, responseType);
        return exchange.getBody();
    }

    protected <T> void update(String uri, T body) {
        HttpEntity<T> requestEntity = gettHttpEntity(body);
        restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, Void.class);
    }

    protected <T> void edit(String uri, T body) {
        HttpEntity<T> requestEntity = gettHttpEntity(body);
        restTemplate.exchange(uri, HttpMethod.PATCH, requestEntity, Void.class);
    }

    private static <T> HttpEntity<T> gettHttpEntity(T body) {
        HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, jsonHeaders);
    }
}