package com.orange.lo.sdk.externalconnector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.lo.sdk.LOApiClientParameters;
import com.orange.lo.sdk.externalconnector.exceptions.ExtConnectorException;
import com.orange.lo.sdk.externalconnector.model.CommandResponse;
import com.orange.lo.sdk.externalconnector.model.DataMessage;
import com.orange.lo.sdk.externalconnector.model.Metadata;
import com.orange.lo.sdk.externalconnector.model.NodeStatus;
import com.orange.lo.sdk.externalconnector.model.Status;
import com.orange.lo.sdk.externalconnector.model.Value;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static com.orange.lo.sdk.LOApiClientParameters.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DataManagementExtConnectorTest {

    private static final String API_KEY = "abcDEfgH123I";
    private static final String EX_CONNECTOR_NODE_ID = "x-con-device-node-id";

    @Mock
    private IMqttClient mqttClient;
    private LOApiClientParameters parameters;
    private DataManagementExtConnector dataManagementExtConnector;

    @BeforeEach
    void setUp() {
        parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();
        this.dataManagementExtConnector = new DataManagementExtConnector(parameters, () -> mqttClient);
        dataManagementExtConnector.connect();
    }

    @Test
    void shouldCallConnectFromMqttClientWhenConnectIsCalled() throws MqttException {
        verify(mqttClient, times(1)).connect(any(MqttConnectOptions.class));
    }

    @Test
    void shouldCallDisconnectFromMqttClientWhenDisconnectIsCalled() throws MqttException {
        dataManagementExtConnector.disconnect();

        verify(mqttClient, times(1)).disconnect();
    }

    @Test
    void shouldPublishMessageWhenCommandResponseIsSentManually() throws MqttException {
        parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .dataManagementExtConnectorCommandCallback(commandRequest -> null)
                .build();
        CommandResponse commandResponse = new CommandResponse("commandRequestId", "nodeId");

        dataManagementExtConnector = new DataManagementExtConnector(parameters, () -> mqttClient);
        dataManagementExtConnector.connect();
        dataManagementExtConnector.sendCommandResponse(commandResponse);

        verify(mqttClient, times(1)).publish(eq(DEFAULT_EXT_CONNECTOR_COMMAND_RESPONSE_TOPIC), any(MqttMessage.class));
    }

    @Test
    void shouldNotSubscribeCommandRequestTopicWhenMessageCallbackIsNotSet() throws MqttException {
        verify(mqttClient, times(0)).subscribe(eq(DEFAULT_EXT_CONNECTOR_COMMAND_REQUEST_TOPIC), eq(DEFAULT_MESSAGE_QOS), any(IMqttMessageListener.class));
    }

    @Test
    void shouldSubscribeCommandRequestTopicWhenMessageCallbackIsSet() throws MqttException {
        parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .dataManagementExtConnectorCommandCallback(commandRequest -> null)
                .build();

        dataManagementExtConnector = new DataManagementExtConnector(parameters, () -> mqttClient);
        dataManagementExtConnector.connect();

        verify(mqttClient, times(1)).subscribe(eq(DEFAULT_EXT_CONNECTOR_COMMAND_REQUEST_TOPIC), eq(DEFAULT_MESSAGE_QOS), any(IMqttMessageListener.class));
    }

    @Test
    void shouldSubscribeToChangedCommandRequestTopicWhenMessageCallbackIsSetAndCommandRequestTopicWasChangedInParameters() throws MqttException {
        String commandRequestTopic = "new/command/request/topic";
        parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .dataManagementExtConnectorCommandCallback((commandRequest) -> null)
                .extConnectorCommandRequestTopic(commandRequestTopic)
                .build();

        dataManagementExtConnector = new DataManagementExtConnector(parameters, () -> mqttClient);
        dataManagementExtConnector.connect();

        verify(mqttClient, times(1)).subscribe(eq(commandRequestTopic), eq(DEFAULT_MESSAGE_QOS), any(IMqttMessageListener.class));
    }

    @Test
    void shouldSendNodeStatusToDefaultStatusTopicAsSelectedNodeIdWhenStatusTopicWasNotChangedInParameters() throws MqttException {
        String expectedTopic = String.format(DEFAULT_EXT_CONNECTOR_STATUS_TOPIC_TEMPLATE, EX_CONNECTOR_NODE_ID);
        NodeStatus nodeStatus = getNodeStatus();
        MqttMessage expectedMessage = toMqttMessage(nodeStatus);

        dataManagementExtConnector.sendStatus(EX_CONNECTOR_NODE_ID, nodeStatus);

        verify(mqttClient, times(1)).publish(eq(expectedTopic), hasSamePayload(expectedMessage));
    }


    @Test
    void shouldSendNodeStatusToChangedStatusTopicAsSelectedNodeIdWhenStatusTopicWasChangedInParameters() throws MqttException {
        String statusTopic = "/%s/new/status/topic";
        String expectedTopic = String.format(statusTopic, EX_CONNECTOR_NODE_ID);
        NodeStatus nodeStatus = getNodeStatus();
        MqttMessage expectedMessage = toMqttMessage(nodeStatus);

        parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .extConnectorStatusTopicTemplate(statusTopic)
                .build();

        dataManagementExtConnector = new DataManagementExtConnector(parameters, () -> mqttClient);
        dataManagementExtConnector.connect();
        dataManagementExtConnector.sendStatus(EX_CONNECTOR_NODE_ID, nodeStatus);

        verify(mqttClient, times(1)).publish(eq(expectedTopic), hasSamePayload(expectedMessage));
    }


    @Test
    void shouldSendMessageToDefaultDataTopicTemplateAsSelectedNodeIdWhenDataTopicTemplateWasNotChangedInParameters() throws MqttException {
        String expectedTopic = String.format(DEFAULT_EXT_CONNECTOR_DATA_TOPIC_TEMPLATE, EX_CONNECTOR_NODE_ID);
        DataMessage dataMessage = getDataMessage();
        MqttMessage expectedMessage = toMqttMessage(dataMessage);

        dataManagementExtConnector.sendMessage(EX_CONNECTOR_NODE_ID, dataMessage);

        verify(mqttClient, times(1)).publish(eq(expectedTopic), hasSamePayload(expectedMessage));
    }

    @Test
    void shouldSendMessageToChangedDataTopicTemplateAsSelectedNodeIdWhenDataTopicTemplateWasChangedInParameters() throws MqttException {
        String dataTopicTemplate = "/%s/new/data/topic";
        String expectedTopic = String.format(dataTopicTemplate, EX_CONNECTOR_NODE_ID);
        DataMessage dataMessage = getDataMessage();
        MqttMessage expectedMessage = toMqttMessage(dataMessage);

        parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .extConnectorDataTopicTemplate(dataTopicTemplate)
                .build();

        dataManagementExtConnector = new DataManagementExtConnector(parameters, () -> mqttClient);
        dataManagementExtConnector.connect();
        dataManagementExtConnector.sendMessage(EX_CONNECTOR_NODE_ID, dataMessage);

        verify(mqttClient, times(1)).publish(eq(expectedTopic), hasSamePayload(expectedMessage));
    }

    private NodeStatus getNodeStatus() {
        NodeStatus nodeStatus = new NodeStatus();
        nodeStatus.setStatus(Status.ONLINE);
        nodeStatus.setCapabilities(new NodeStatus.Capabilities(true));
        return nodeStatus;
    }

    private DataMessage getDataMessage() {
        Value payload = new Value("15;25");
        Metadata metadata = new Metadata("test_csv");
        DataMessage dataMessage = new DataMessage();
        dataMessage.setValue(payload);
        dataMessage.setMetadata(metadata);
        return dataMessage;
    }

    private MqttMessage toMqttMessage(Object dataMessage) {
        try {
            String payload = new ObjectMapper().writeValueAsString(dataMessage);
            MqttMessage msg = new MqttMessage();
            msg.setQos(parameters.getMessageQos());
            msg.setPayload(payload.getBytes());
            return msg;
        } catch (JsonProcessingException e) {
            throw new ExtConnectorException(e);
        }
    }

    private static MqttMessage hasSamePayload(MqttMessage expectedMessage) {
        return argThat(givenMessage -> {
            try {
                byte[] expectedPayload = expectedMessage.getPayload();
                byte[] givenPayload = givenMessage.getPayload();
                return Arrays.equals(givenPayload, expectedPayload);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        });
    }
}