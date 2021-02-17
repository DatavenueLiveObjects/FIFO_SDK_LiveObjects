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
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import java.util.UUID;

public class MqttClientFactoryImpl implements MqttClientFactory {

    private static final String SERVER_URI_FORMAT = "ssl://%s:8883";
    private final String hostname;
	private final String dataDir;

    public MqttClientFactoryImpl(String hostname, String dataDir) {
        this.hostname = hostname;
		this.dataDir = dataDir;
    }

    @Override
    public IMqttClient getMqttClient() {
        try {
            return new MqttClient(String.format(SERVER_URI_FORMAT, hostname), UUID.randomUUID().toString(), new MqttDefaultFilePersistence(dataDir));
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }
}