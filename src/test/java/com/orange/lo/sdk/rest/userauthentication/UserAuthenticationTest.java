package com.orange.lo.sdk.rest.userauthentication;

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

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationTest {

    private static final String TENANT_ID = "abcDEfgH123I";

    @Mock
    private RestTemplate restTemplate;
    private UserAuthentication userAuthentication;

    @BeforeEach
    void setUp() {
        this.userAuthentication = new UserAuthentication(() -> restTemplate);
    }

    @Test
    void getYourTenantId() {
        String expectedEndpoint = "/v0/whoami";
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("content", TENANT_ID);
        ResponseEntity<Map<String, String>> groupResponseEntity = ResponseEntity.ok(stringMap);


        when(restTemplate.exchange(eq(expectedEndpoint),
                eq(HttpMethod.GET),
                any(),
                ArgumentMatchers.<ParameterizedTypeReference<Map<String, String>>>any())
        ).thenReturn(groupResponseEntity);
        String yourTenantId = userAuthentication.getYourTenantId();

        assertEquals(TENANT_ID, yourTenantId);
    }
}