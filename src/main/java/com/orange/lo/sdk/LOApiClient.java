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
import com.orange.lo.sdk.rest.RestTemplateFactoryImpl;
import com.orange.lo.sdk.rest.apikeys.ApiKeys;
import com.orange.lo.sdk.rest.devicemanagement.DeviceManagement;
import com.orange.lo.sdk.rest.userauthentication.UserAuthentication;

public class LOApiClient {

    private DeviceManagement deviceManagement;
    private DataManagementFifo dataManagementFifo;
    private DataManagementExtConnector dataManagementExtConnector;
    private UserAuthentication userAuthentication;
    private ApiKeys apiKeys;

	public LOApiClient(LOApiClientParameters parameters) {
    	RestTemplateFactoryImpl restTemplateFactoryImpl = new RestTemplateFactoryImpl(parameters);
        this.deviceManagement = new DeviceManagement(restTemplateFactoryImpl);
        MqttClientFactory mqttClientFactory = new MqttClientFactoryImpl(parameters.getHostname(), parameters.getMqttPersistenceDataDir());
        this.dataManagementFifo = new DataManagementFifo(parameters, mqttClientFactory);
        this.dataManagementExtConnector = new DataManagementExtConnector(parameters, mqttClientFactory);
        this.apiKeys = new ApiKeys(restTemplateFactoryImpl);
        this.userAuthentication = new UserAuthentication(restTemplateFactoryImpl);
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

    public ApiKeys getApiKeys() {
		return apiKeys;
	}

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }
}
