package com.orange.lo.sdk.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HttpHeaderInterceptorTest {

    @Mock
    private HttpRequest request;
    @Mock
    private HttpHeaders headers;
    @Mock
    private ClientHttpRequestExecution execution;
    private byte[] body;
    private HttpHeaderInterceptor httpHeaderInterceptor;

    @BeforeEach
    void setUp() {
        when(request.getHeaders()).thenReturn(headers);
        body = new byte[0];
        httpHeaderInterceptor = new HttpHeaderInterceptor("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void shouldCorrectlyAddHeadersToRequestAndCallExecutionWhenInterceptMethodIsCalled() throws IOException {
        httpHeaderInterceptor.intercept(request, body, execution);

        verify(headers, times(1)).add(eq("Content-Type"), eq(MediaType.APPLICATION_JSON_VALUE));
        verify(execution, times(1)).execute(eq(request), eq(body));
    }
}