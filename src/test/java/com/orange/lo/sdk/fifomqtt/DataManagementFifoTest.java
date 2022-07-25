package com.orange.lo.sdk.fifomqtt;

import com.orange.lo.sdk.LOApiClientParameters;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DataManagementFifoTest {

    private static final String API_KEY = "abcDEfgH123I";

    @Mock
    private IMqttClient mqttClient;
    private DataManagementFifo dataManagementFifo;

    @BeforeEach
    void setUp() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .topics(Arrays.asList("topic-01", "topic-02"))
                .dataManagementMqttCallback(System.out::println)
                .build();
        this.dataManagementFifo = new DataManagementFifo(parameters, () -> mqttClient);
    }

    @Test
    void shouldCallMqttClientWhenDisconnect() throws MqttException {
        dataManagementFifo.disconnect();

        verify(mqttClient, times(1)).disconnect();
    }

    @Test
    void shouldCallMqttClientWhenSubscribe() throws MqttException {
        dataManagementFifo.connectAndSubscribe();

        verify(mqttClient, times(1)).connect(any(MqttConnectOptions.class));
        verify(mqttClient, times(1)).subscribe(any(String[].class), any(int[].class), any(IMqttMessageListener[].class));
    }

    @Test
    void shouldCallMqttClientWhenIsCheckingConnectionStatus() {
        dataManagementFifo.isConnected();

        verify(mqttClient, times(1)).isConnected();
    }
}