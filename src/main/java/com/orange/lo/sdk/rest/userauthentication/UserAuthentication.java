/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.userauthentication;

import com.orange.lo.sdk.rest.ResourceClient;
import com.orange.lo.sdk.rest.RestTemplateFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class UserAuthentication extends ResourceClient {

    private static final String WHOAMI_ENDPOINT = "/v0/whoami";
    private static final String CONTENT = "content";


    public UserAuthentication(RestTemplateFactory restTemplateFactory) {
        super(restTemplateFactory);
    }

    public String getYourTenantId() {
        String uri = UriComponentsBuilder.fromUriString(WHOAMI_ENDPOINT)
                .toUriString();

        ParameterizedTypeReference<Map<String, String>> typeRef = new ParameterizedTypeReference<Map<String, String>>() {
        };
        Map<String, String> s = get(uri, typeRef);
        return s.get(CONTENT);
    }
}
