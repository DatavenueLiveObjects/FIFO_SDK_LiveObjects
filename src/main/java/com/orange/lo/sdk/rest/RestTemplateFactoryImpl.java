/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest;

import com.orange.lo.sdk.LOApiClientParameters;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

public class RestTemplateFactoryImpl implements RestTemplateFactory {

    private static final String BASE_URI_FORMAT = "https://%s/api";
    private final LOApiClientParameters parameters;

    public RestTemplateFactoryImpl(LOApiClientParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(String.format(BASE_URI_FORMAT, parameters.getHostname())));
        restTemplate.getInterceptors().add(new HttpHeaderInterceptor("Content-Type", MediaType.APPLICATION_JSON_VALUE));
        restTemplate.getInterceptors().add(new HttpHeaderInterceptor("X-API-KEY", parameters.getApiKey()));
        restTemplate.getInterceptors().add(new HttpHeaderInterceptor("X-Total-Count", "true"));

        return restTemplate;
    }
}