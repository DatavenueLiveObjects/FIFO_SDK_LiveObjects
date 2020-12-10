/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.externalconnector.exceptions;

public class ExtConnectorException extends RuntimeException {

    private static final long serialVersionUID = 224334629639217345L;

    public ExtConnectorException() {
        super();
    }

    public ExtConnectorException(String message) {
        super(message);
    }

    public ExtConnectorException(Throwable throwable) {
        super(throwable);
    }

}
