/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.externalconnector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.lo.sdk.LOApiClientParameters;
import com.orange.lo.sdk.externalconnector.exceptions.ExtConnectorException;
import com.orange.lo.sdk.externalconnector.model.AcknowledgementMode;
import com.orange.lo.sdk.externalconnector.model.CommandRequest;
import com.orange.lo.sdk.externalconnector.model.CommandResponse;
import com.orange.lo.sdk.externalconnector.model.DataMessage;
import com.orange.lo.sdk.externalconnector.model.NodeStatus;
import com.orange.lo.sdk.mqtt.AbstractDataManagementMqtt;
import com.orange.lo.sdk.mqtt.MqttClientFactory;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;

public class DataManagementExtConnector extends AbstractDataManagementMqtt {

    public DataManagementExtConnector(LOApiClientParameters parameters, MqttClientFactory mqttClientFactory,
                                      String apiKey) {
        super(parameters, mqttClientFactory, apiKey);
    }

    @Override
    public void connect() {
        super.connect();
        LOApiClientParameters parameters = getParameters();
        if (parameters.getDataManagementExtConnectorCommandCallback() != null) {
            receiveCommands();
        }
    }

    public void sendStatus(String nodeId, NodeStatus nodeStatus) {
        LOApiClientParameters parameters = getParameters();
        MqttMessage msg = prepareMqttMessage(nodeStatus);
        String topic = String.format(parameters.getExtConnectorStatusTopicTemplate(), nodeId);
        publish(topic, msg);
    }

    public void sendMessage(String nodeId, DataMessage dataMessage) {
        LOApiClientParameters parameters = getParameters();
        MqttMessage msg = prepareMqttMessage(dataMessage);
        String topic = String.format(parameters.getExtConnectorDataTopicTemplate(), nodeId);
        publish(topic, msg);
    }

    private MqttMessage prepareMqttMessage(Object message) {
        try {
            LOApiClientParameters parameters = getParameters();
            ObjectMapper objectMapper = getObjectMapper();
            String payload = objectMapper.writeValueAsString(message);
            MqttMessage msg = new MqttMessage();
            msg.setQos(parameters.getMessageQos());
            msg.setPayload(payload.getBytes());
            return msg;
        } catch (JsonProcessingException e) {
            throw new ExtConnectorException(e);
        }
    }

    private void sendCommandResponse(CommandResponse commandResponse) {
        MqttMessage msg = prepareMqttMessage(commandResponse);
        LOApiClientParameters parameters = getParameters();
        publish(parameters.getExtConnectorCommandResponseTopic(), msg);
    }

    private void receiveCommands() {
        LOApiClientParameters parameters = getParameters();
        subscribe(parameters.getExtConnectorCommandRequestTopic(), parameters.getMessageQos(), this::messageArrived);
    }

    private void messageArrived(String topic, MqttMessage mqttMessage) throws IOException {
        ObjectMapper objectMapper = getObjectMapper();
        CommandRequest commandRequest = objectMapper.readValue(mqttMessage.getPayload(), CommandRequest.class);
        LOApiClientParameters parameters = getParameters();
        DataManagementExtConnectorCommandCallback dataManagementExternalConnectorCommandCallback = parameters.getDataManagementExtConnectorCommandCallback();
        Object response = dataManagementExternalConnectorCommandCallback.onCommandRequest(commandRequest);
        if (!isAckModeNone(commandRequest)) {
            CommandResponse commandResponse = new CommandResponse(commandRequest.getId(), commandRequest.getNodeId());
            commandResponse.setResponse(response);
            sendCommandResponse(commandResponse);
        }
    }

    private boolean isAckModeNone(CommandRequest commandRequest) {
        AcknowledgementMode ackMode = commandRequest.getAckMode();
        return AcknowledgementMode.NONE.equals(ackMode);
    }

    @Override
    protected MqttConnectOptions getMqttConnectionOptions() {
        LOApiClientParameters parameters = getParameters();
        MqttConnectOptions opts = super.getMqttConnectionOptions();
        opts.setUserName(parameters.getExtConnectorUsername());
        return opts;
    }

}
