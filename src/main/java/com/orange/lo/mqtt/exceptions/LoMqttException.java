/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.mqtt.exceptions;

public class LoMqttException extends RuntimeException {

    private static final long serialVersionUID = 224334629639217345L;

    public LoMqttException() {
        super();
    }

    public LoMqttException(String message) {
        super(message);
    }

    public LoMqttException(Throwable throwable) {
        super(throwable);
    }
}