/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.mqtt;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractDataManagementMqtt {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final IMqttClient mqttClient;
    private final ObjectMapper objectMapper;
    private final LOApiClientParameters parameters;
	private final MqttReconnectCallback mqttReconnectCallback;

    public AbstractDataManagementMqtt(LOApiClientParameters parameters, MqttClientFactory mqttClientFactory) {
        DataManagementReconnectCallback reconnectCallback = parameters.getDataManagementReconnectCallback();
        this.mqttReconnectCallback = new MqttReconnectCallback(reconnectCallback);
        this.mqttClient = mqttClientFactory.getMqttClient();
        this.mqttClient.setCallback(mqttReconnectCallback);
        this.parameters = parameters;
        this.objectMapper = new ObjectMapper();
    }

    public void disconnect() {
        try {
            LOG.info("Mqtt client is disconnecting...");
            mqttClient.disconnect();
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }

    public void connect() {
        try {
            if (!mqttClient.isConnected()) {
                LOG.info("Mqtt client starts connecting...");
                if (parameters.isManualAck()) mqttClient.setManualAcks(true);
                MqttConnectOptions opts = getMqttConnectionOptions();
                mqttClient.connect(opts);
            }
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }

    protected void sendACK(int messageId, int qos) {
        try {
            mqttClient.messageArrivedComplete(messageId, qos);
        } catch (MqttException e) {
            throw new LoMqttException(e);
        }
    }

    public boolean isConnected() {
        return mqttClient.isConnected();
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
            // To remove duplicates causing the error "This Topic Is Already Subscribed In The Same MQTT Connection"
            //Helpers
            List<String> topicFiltersList = new ArrayList<>();
            List<Integer> qosList = new ArrayList<>();
            List<IMqttMessageListener> listenersList = new ArrayList<>();

            //Finding unique values from Topics Filters and their qos and listeners
            for (int i = 0; i < topicFilters.length; i++) {
                String topicFilter = topicFilters[i];
                if (!topicFiltersList.contains(topicFilter)) {
                    topicFiltersList.add(topicFilter);
                    qosList.add(qos[i] > 2 ? parameters.getMessageQos() : qos[i]);
                    listenersList.add(messageListeners[i]);
                }
            }

            //Transformation to subscription input structures
            String[] uniqueTopicFilters = topicFiltersList.toArray(new String[0]);
            int[] qosForUniqueFilters = qosList.stream().mapToInt(i -> i).toArray();
            IMqttMessageListener[] listenersForUniqueFilters = listenersList.toArray(new IMqttMessageListener[0]);

            mqttClient.subscribe(uniqueTopicFilters, qosForUniqueFilters, listenersForUniqueFilters);
            mqttReconnectCallback.addSubscriptions(uniqueTopicFilters, qosForUniqueFilters, listenersForUniqueFilters);
            LOG.info("Subscribed mqtt topics: {}", (Object) uniqueTopicFilters);
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
        private final DataManagementReconnectCallback reconnectCallback;

        public MqttReconnectCallback(DataManagementReconnectCallback reconnectCallback) {
            this.reconnectCallback = reconnectCallback;
        }

        public void addSubscriptions(String[] topicFilters, int[] qos, IMqttMessageListener[] messageListeners) {
            // To remove duplicates causing the error "This Topic Is Already Subscribed In The Same MQTT Connection"
            for (int i = 0; i < topicFilters.length; i++) {
                String topicFilter = topicFilters[i];
                if (!topicFiltersList.contains(topicFilter)) {
                    topicFiltersList.add(topicFilter);
                    qosList.add(qos[i] > 2 ? parameters.getMessageQos() : qos[i]);
                    listenersList.add(messageListeners[i]);
                }
            }
            LOG.info("MqttReconnectCallback topicFiltersList: {}", topicFiltersList);
            LOG.info("MqttReconnectCallback qosList: {}", qosList);
            LOG.info("MqttReconnectCallback listenersList: {}", listenersList);
        }

        public void connectionLost(Throwable cause) {
            LOG.error("Connection lost: {}", cause.getMessage());
            if (thereIsA(reconnectCallback)) {
                reconnectCallback.connectionLost(cause);
            }
        }

		public void messageArrived(String topic, MqttMessage message) throws Exception {
		}

		public void deliveryComplete(IMqttDeliveryToken token) {
		}

		public void connectComplete(boolean reconnect, String serverURI) {
            LOG.info("Connect complete. Reconnect: {}", reconnect);
			if(reconnect) {
				subscribe(
						topicFiltersList.toArray(new String[0]), 
						qosList.stream().mapToInt(Integer::intValue).toArray(),
						listenersList.toArray(new IMqttMessageListener[0]));
			}
            if (thereIsA(reconnectCallback)) {
                reconnectCallback.connectComplete(reconnect, serverURI);
            }
		}

        private boolean thereIsA(Object object) {
            return Objects.nonNull(object);
        }
	}
}
