/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo;

import com.orange.lo.mqtt.DataManagementMqtt;
import com.orange.lo.mqtt.MqttClientFactory;
import com.orange.lo.mqtt.MqttClientFactoryImpl;
import com.orange.lo.rest.devicemanagement.DeviceManagement;

public class LOApiClient {

    private DeviceManagement deviceManagement;
    private DataManagementMqtt dataManagementMqtt;

    public LOApiClient(LOApiClientParameters parameters) {
        this.deviceManagement = new DeviceManagement(parameters);
        MqttClientFactory mqttClientFactory = new MqttClientFactoryImpl(parameters.getHostname());
        this.dataManagementMqtt = new DataManagementMqtt(parameters, mqttClientFactory);
    }

    public DeviceManagement getDeviceManagement() {
        return deviceManagement;
    }

    public DataManagementMqtt getDataManagementMqtt() {
        return dataManagementMqtt;
    }
}