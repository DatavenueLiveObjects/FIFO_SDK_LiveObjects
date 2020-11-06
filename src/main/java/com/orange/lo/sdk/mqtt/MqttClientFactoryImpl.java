/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.mqtt;

import com.orange.lo.sdk.mqtt.exceptions.LoMqttException;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;

public class MqttClientFactoryImpl implements MqttClientFactory {

    private static final String SERVER_URI_FORMAT = "ssl://%s:8883";
    private final String hostname;

    public MqttClientFactoryImpl(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public IMqttClient getMqttClient() {
        try {
            return new MqttClient(String.format(SERVER_URI_FORMAT, hostname), UUID.randomUUID().toString());
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }
}