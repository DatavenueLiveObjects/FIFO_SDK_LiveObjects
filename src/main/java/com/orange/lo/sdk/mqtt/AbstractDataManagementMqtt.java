/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.mqtt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.lo.sdk.LOApiClientParameters;
import com.orange.lo.sdk.mqtt.exceptions.LoMqttException;


public abstract class AbstractDataManagementMqtt {

    private final IMqttClient mqttClient;
    private final ObjectMapper objectMapper;
    private final LOApiClientParameters parameters;
	private final MqttReconnectCallback mqttReconnectCallback;

    public AbstractDataManagementMqtt(LOApiClientParameters parameters, MqttClientFactory mqttClientFactory) {
    	this.mqttReconnectCallback = new MqttReconnectCallback();
        this.mqttClient = mqttClientFactory.getMqttClient();
        this.mqttClient.setCallback(mqttReconnectCallback);
        this.parameters = parameters;
        this.objectMapper = new ObjectMapper();
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
        opts.setPassword(parameters.getApiKey().toCharArray());
        opts.setAutomaticReconnect(parameters.isAutomaticReconnect());
        opts.setKeepAliveInterval(parameters.getKeepAliveIntervalSeconds());
        opts.setAutomaticReconnect(parameters.isAutomaticReconnect());
        opts.setConnectionTimeout(parameters.getConnectionTimeout());
        opts.setCleanSession(parameters.isCleanSession());
        opts.setMaxInflight(parameters.getMaxInflight());
                            
        return opts;
    }

    protected void subscribe(String topicFilter, int qos, IMqttMessageListener messageListener) {
        subscribe(new String[] {topicFilter}, new int[] {qos}, new IMqttMessageListener[] {messageListener});
    }

    protected void subscribe(String[] topicFilters, int[] qos, IMqttMessageListener[] messageListeners) {
        try {
            mqttClient.subscribe(topicFilters, qos, messageListeners);
            mqttReconnectCallback.addSubscriptions(topicFilters, qos, messageListeners);
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
    
    class MqttReconnectCallback implements MqttCallbackExtended {

		private final List<String>  topicFiltersList = new ArrayList<>();
		private final List<Integer> qosList = new ArrayList<>();
		private final List<IMqttMessageListener>  listenersList = new ArrayList<>();

		public void addSubscriptions(String[] topicFilters, int[] qos, IMqttMessageListener[] messageListeners) {
			topicFiltersList.addAll(Arrays.asList(topicFilters));
			qosList.addAll(Arrays.stream(qos).boxed().collect(Collectors.toList()));
			listenersList.addAll(Arrays.asList(messageListeners));
		}

		public void connectionLost(Throwable cause) {
		}

		public void messageArrived(String topic, MqttMessage message) throws Exception {
		}

		public void deliveryComplete(IMqttDeliveryToken token) {
		}

		public void connectComplete(boolean reconnect, String serverURI) {
			if(reconnect) {
				subscribe(
						topicFiltersList.toArray(new String[0]), 
						qosList.stream().mapToInt(Integer::intValue).toArray(),
						listenersList.toArray(new IMqttMessageListener[0]));
			}
		}
	}
}
