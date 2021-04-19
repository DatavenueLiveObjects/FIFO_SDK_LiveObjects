/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.lo.sdk.LOApiClientParameters;
import com.orange.lo.sdk.mqtt.exceptions.LoMqttException;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public abstract class AbstractDataManagementMqtt {

    private final IMqttClient mqttClient;
    private final ObjectMapper objectMapper;
    private final LOApiClientParameters parameters;
    private final String apiKey;

    protected AbstractDataManagementMqtt(LOApiClientParameters parameters, MqttClientFactory mqttClientFactory,
                                      String apiKey) {
        this.mqttClient = mqttClientFactory.getMqttClient();
        this.parameters = parameters;
        this.objectMapper = new ObjectMapper();
        this.apiKey = apiKey;
    }

    public void disconnect() {
        try {
            mqttClient.disconnect();
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }

    public void connect() {
        try {
            if (!mqttClient.isConnected()) {
                MqttConnectOptions opts = getMqttConnectionOptions();
                mqttClient.connect(opts);
            }
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }

    protected MqttConnectOptions getMqttConnectionOptions() {
        MqttConnectOptions opts = new MqttConnectOptions();
        opts.setUserName(parameters.getUsername());
        opts.setPassword(apiKey.toCharArray());
        opts.setAutomaticReconnect(parameters.isAutomaticReconnect());
        opts.setKeepAliveInterval(parameters.getKeepAliveIntervalSeconds());
        opts.setAutomaticReconnect(parameters.isAutomaticReconnect());
        opts.setConnectionTimeout(parameters.getConnectionTimeout());
        opts.setCleanSession(parameters.isCleanSession());
        opts.setMaxInflight(parameters.getMaxInflight());

        return opts;
    }

    protected void subscribe(String topicFilter, int qos, IMqttMessageListener messageListener) {
        try {
            mqttClient.subscribe(topicFilter, qos, messageListener);
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }

    protected void subscribe(String[] topicFilters, int[] qos, IMqttMessageListener[] messageListeners) {
        try {
            mqttClient.subscribe(topicFilters, qos, messageListeners);
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }

    protected void publish(String topic, MqttMessage msg) {
        try {
            mqttClient.publish(topic, msg);
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }

    protected ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    protected LOApiClientParameters getParameters() {
        return parameters;
    }
}
