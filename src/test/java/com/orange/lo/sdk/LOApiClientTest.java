package com.orange.lo.sdk;

import com.orange.lo.sdk.externalconnector.DataManagementExtConnector;
import com.orange.lo.sdk.fifomqtt.DataManagementFifo;
import com.orange.lo.sdk.rest.devicemanagement.DeviceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LOApiClientTest {

    private static final String API_KEY = "abcDEfgH123I";
    private LOApiClient loApiClient;

    @BeforeEach
    void setUp() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();
        this.loApiClient = new LOApiClient(parameters);
    }

    @Test
    void shouldCorrectlyCreateDeviceManagement() {
        DeviceManagement deviceManagement = loApiClient.getDeviceManagement();
        assertNotNull(deviceManagement);
    }

    @Test
    void shouldCorrectlyCreateDataManagementFifo() {
        DataManagementFifo dataManagementFifo = loApiClient.getDataManagementFifo();
        assertNotNull(dataManagementFifo);
    }

    @Test
    void shouldCorrectlyCreateDataManagementExtConnector() {
        DataManagementExtConnector dataManagementFifo = loApiClient.getDataManagementExtConnector();
        assertNotNull(dataManagementFifo);
    }
}