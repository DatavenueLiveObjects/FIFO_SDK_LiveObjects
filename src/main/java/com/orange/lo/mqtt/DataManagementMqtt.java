/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.mqtt;

import com.orange.lo.LOApiClientParameters;
import com.orange.lo.mqtt.exceptions.LoMqttException;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Collections;

public class DataManagementMqtt {

    private static final String FIFO_PREFIX = "fifo/";

    private final LOApiClientParameters parameters;
    private final DataManagementMqttCallback callback;
    private final IMqttClient mqttClient;

    public DataManagementMqtt(LOApiClientParameters parameters, MqttClientFactory mqttClientFactory) {
        this.parameters = parameters;
        this.callback = parameters.getDataManagementMqttCallback();
        this.mqttClient = mqttClientFactory.getMqttClient();
    }

    public void disconnect() {
        try {
            mqttClient.disconnect();
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }

    public void subscribe() {
        try {
            connect();
            prepareParamsAndSubscribe();
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }

    private void connect() throws MqttException {
        if (!mqttClient.isConnected()) {
            MqttConnectOptions opts = getMqttConnectionOptions();
            mqttClient.connect(opts);
        }
    }

    private MqttConnectOptions getMqttConnectionOptions() {
        MqttConnectOptions opts = new MqttConnectOptions();
        opts.setUserName(parameters.getUsername());
        opts.setPassword(parameters.getApiKey().toCharArray());
        opts.setAutomaticReconnect(parameters.isAutomaticReconnect());
        opts.setKeepAliveInterval(parameters.getKeepAliveIntervalSeconds());
        opts.setAutomaticReconnect(parameters.isAutomaticReconnect());
        opts.setConnectionTimeout(parameters.getConnectionTimeout());
        return opts;
    }

    private void prepareParamsAndSubscribe() throws MqttException {
        String[] topicsArray = getTopicsArray();
        int[] qosArray = getQosArray(topicsArray.length);
        IMqttMessageListener[] iMqttMessageListenerArray = getMqttMessageListenersArray(topicsArray.length);
        mqttClient.subscribe(topicsArray, qosArray, iMqttMessageListenerArray);
    }

    private IMqttMessageListener[] getMqttMessageListenersArray(int topicsSize) {
        IMqttMessageListener messageArrived = this::messageArrived;
        return Collections.nCopies(topicsSize, messageArrived).toArray(new IMqttMessageListener[topicsSize]);
    }

    private int[] getQosArray(int topicsSize) {
        return Collections.nCopies(topicsSize, parameters.getMessageQos()).stream().mapToInt(i -> i).toArray();
    }

    private String[] getTopicsArray() {
        return parameters.getTopics().stream().map(s -> FIFO_PREFIX + s).toArray(String[]::new);
    }

    private void messageArrived(String topic, MqttMessage mqttMessage) {
        callback.onMessage(mqttMessage.toString());
    }
}