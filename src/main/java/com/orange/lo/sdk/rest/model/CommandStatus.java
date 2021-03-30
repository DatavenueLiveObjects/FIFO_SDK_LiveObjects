/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.model;

public enum CommandStatus {
    CANCELED,
    ERROR,
    EXPIRED,
    PENDING,
    PROCESSED,
    PROCESSING,
    RETRYING,
    UNKNOWN;
}
