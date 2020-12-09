package com.orange.lo.sdk;

import com.orange.lo.sdk.externalconnector.DataManagementExtConnector;
import com.orange.lo.sdk.externalconnector.model.DataMessage;
import com.orange.lo.sdk.externalconnector.model.Metadata;
import com.orange.lo.sdk.externalconnector.model.NodeStatus;
import com.orange.lo.sdk.externalconnector.model.Status;
import com.orange.lo.sdk.externalconnector.model.Value;

public class Main {

    public static void main(String[] args) {

        String nodeId = "device_demo_google_1";
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey("00ffed77872c46d6a5c96735db40eb0f")
                .build();
        LOApiClient loApiClient = new LOApiClient(parameters);
        DataManagementExtConnector dataManagementExtConnector = loApiClient.getDataManagementExtConnector();
        dataManagementExtConnector.connect();

        NodeStatus nodeStatus = new NodeStatus();
        nodeStatus.setStatus(Status.ONLINE);
        nodeStatus.setCapabilities(new NodeStatus.Capabilities(true));

        dataManagementExtConnector.sendStatus(nodeId, nodeStatus);

        Value payload = new Value("15;30");
        Metadata metadata = new Metadata("test_csv");
        DataMessage dataMessage = new DataMessage();
        dataMessage.setValue(payload);
        dataMessage.setMetadata(metadata);

        dataManagementExtConnector.sendMessage(nodeId, dataMessage);

    }
}
