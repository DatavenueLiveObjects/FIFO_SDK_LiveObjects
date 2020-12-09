/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk;

import com.orange.lo.sdk.externalconnector.DataManagementExtConnector;
import com.orange.lo.sdk.fifomqtt.DataManagementFifo;
import com.orange.lo.sdk.mqtt.MqttClientFactory;
import com.orange.lo.sdk.mqtt.MqttClientFactoryImpl;
import com.orange.lo.sdk.rest.devicemanagement.DeviceManagement;

public class LOApiClient {

    private DeviceManagement deviceManagement;
    private DataManagementFifo dataManagementFifo;
    private DataManagementExtConnector dataManagementExtConnector;

    public LOApiClient(LOApiClientParameters parameters) {
        this.deviceManagement = new DeviceManagement(parameters);
        MqttClientFactory mqttClientFactory = new MqttClientFactoryImpl(parameters.getHostname());
        this.dataManagementFifo = new DataManagementFifo(parameters, mqttClientFactory);
        this.dataManagementExtConnector = new DataManagementExtConnector(parameters, mqttClientFactory);
    }

    public DeviceManagement getDeviceManagement() {
        return deviceManagement;
    }

    public DataManagementFifo getDataManagementFifo() {
        return dataManagementFifo;
    }

    public DataManagementExtConnector getDataManagementExtConnector() {
        return dataManagementExtConnector;
    }
}