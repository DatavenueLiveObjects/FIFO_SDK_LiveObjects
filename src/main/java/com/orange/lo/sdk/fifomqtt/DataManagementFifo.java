/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.fifomqtt;

import com.orange.lo.sdk.LOApiClientParameters;
import com.orange.lo.sdk.mqtt.AbstractDataManagementMqtt;
import com.orange.lo.sdk.mqtt.MqttClientFactory;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Collections;
import java.util.List;

public class DataManagementFifo extends AbstractDataManagementMqtt {

    private static final String FIFO_PREFIX = "fifo/";

    private final DataManagementFifoCallback callback;

    public DataManagementFifo(LOApiClientParameters parameters, MqttClientFactory mqttClientFactory) {
        super(parameters, mqttClientFactory);
        this.callback = parameters.getDataManagementFifoCallback();
    }

    public void connectAndSubscribe() {
        connect();
        prepareParamsAndSubscribe();
    }

    private void prepareParamsAndSubscribe() {
        String[] topicsArray = getTopicsArray();
        int[] qosArray = getQosArray(topicsArray.length);
        IMqttMessageListener[] iMqttMessageListenerArray = getMqttMessageListenersArray(topicsArray.length);
        subscribe(topicsArray, qosArray, iMqttMessageListenerArray);
    }

    private IMqttMessageListener[] getMqttMessageListenersArray(int topicsSize) {
        IMqttMessageListener messageArrived = this::messageArrived;
        return Collections.nCopies(topicsSize, messageArrived).toArray(new IMqttMessageListener[topicsSize]);
    }

    private int[] getQosArray(int topicsSize) {
        LOApiClientParameters parameters = getParameters();
        int messageQos = parameters.getMessageQos();
        return Collections.nCopies(topicsSize, messageQos).stream().mapToInt(i -> i).toArray();
    }

    private String[] getTopicsArray() {
        LOApiClientParameters parameters = getParameters();
        List<String> topics = parameters.getTopics();
        return topics.stream().map(s -> FIFO_PREFIX + s).toArray(String[]::new);
    }

    private void messageArrived(String topic, MqttMessage mqttMessage) {
        callback.onMessage(mqttMessage.toString());
    }
}