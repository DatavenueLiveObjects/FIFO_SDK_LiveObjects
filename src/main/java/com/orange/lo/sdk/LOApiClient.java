/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk;

import com.orange.lo.sdk.externalconnector.DataManagementExtConnector;
import com.orange.lo.sdk.fifomqtt.DataManagementFifo;
import com.orange.lo.sdk.mqtt.MqttClientFactory;
import com.orange.lo.sdk.mqtt.MqttClientFactoryImpl;
import com.orange.lo.sdk.rest.RestTemplateFactoryImpl;
import com.orange.lo.sdk.rest.apikeys.ApiKeys;
import com.orange.lo.sdk.rest.devicemanagement.DeviceManagement;
import com.orange.lo.sdk.rest.model.ApiKey;
import com.orange.lo.sdk.rest.model.AuthenticationRequest;
import com.orange.lo.sdk.rest.model.AuthenticationResponse;
import com.orange.lo.sdk.rest.userauthentication.UserAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class LOApiClient {

    private static final String AUTH_ENDPOINT = "/v0/auth";
    private DeviceManagement deviceManagement;
    private DataManagementFifo dataManagementFifo;
    private DataManagementExtConnector dataManagementExtConnector;
    private UserAuthentication userAuthentication;
    private ApiKeys apiKeys;

    public LOApiClient(LOApiClientParameters parameters) {
        String apiKey = getApiKey(parameters);
        RestTemplateFactoryImpl restTemplateFactoryImpl = new RestTemplateFactoryImpl(parameters, apiKey);
        this.deviceManagement = new DeviceManagement(restTemplateFactoryImpl);
        MqttClientFactory mqttClientFactory = new MqttClientFactoryImpl(parameters.getHostname(), parameters.getMqttPersistenceDataDir());
        this.dataManagementFifo = new DataManagementFifo(parameters, mqttClientFactory, apiKey);
        this.dataManagementExtConnector = new DataManagementExtConnector(parameters, mqttClientFactory, apiKey);
        this.apiKeys = new ApiKeys(restTemplateFactoryImpl);
        this.userAuthentication = new UserAuthentication(restTemplateFactoryImpl);
    }

    public DeviceManagement getDeviceManagement() {
        return deviceManagement;
    }

    public DataManagementFifo getDataManagementFifo() {
        return dataManagementFifo;
    }

    public DataManagementExtConnector getDataManagementExtConnector() {
        return dataManagementExtConnector;
    }

    public ApiKeys getApiKeys() {
		return apiKeys;
	}

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    private String getApiKey(LOApiClientParameters parameters) {
        String apiKey;
        if (parameters.getCredentials() instanceof LoginAndPasswordCredentials) {
            apiKey = authenticateUser(parameters);
        } else {
            ApiKeyCredentials credentials = (ApiKeyCredentials) parameters.getCredentials();
            apiKey = credentials.getApiKey();
        }
        return apiKey;
    }

    private String authenticateUser(LOApiClientParameters parameters) {
        LoginAndPasswordCredentials credentials = (LoginAndPasswordCredentials) parameters.getCredentials();

        String uri = UriComponentsBuilder.fromPath(AUTH_ENDPOINT)
                .queryParam("cookie", false)
                .build()
                .toUriString();

        RestTemplateFactoryImpl restTemplateFactoryImpl = new RestTemplateFactoryImpl(parameters, null);
        RestTemplate restTemplate = restTemplateFactoryImpl.getRestTemplate();
        AuthenticationRequest request = new AuthenticationRequest().withLogin(credentials.getLogin()).withPassword(credentials.getPassword());

        ResponseEntity<AuthenticationResponse> exchange = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(request), AuthenticationResponse.class);
        AuthenticationResponse body = exchange.getBody();

        ApiKey apiKey = body.getApiKey();
        return apiKey.getValue();
    }
}