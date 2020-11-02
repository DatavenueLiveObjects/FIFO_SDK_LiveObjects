/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;

public interface MqttClientFactory {

    IMqttClient getMqttClient();
}