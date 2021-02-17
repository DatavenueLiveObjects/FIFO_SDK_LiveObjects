package com.orange.lo.sdk.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MqttClientFactoryImplTest {

    @Test
    void shouldCorrectlyCreateMqttClient() {
        String hostname = "hostname.com";
        String dataDir = System.getProperty("java.io.tmpdir");
        MqttClientFactoryImpl factory = new MqttClientFactoryImpl(hostname, dataDir);

        IMqttClient mqttClient = factory.getMqttClient();
        assertNotNull(mqttClient);
        String serverURI = mqttClient.getServerURI();
        assertEquals("ssl://hostname.com:8883", serverURI);
    }
}