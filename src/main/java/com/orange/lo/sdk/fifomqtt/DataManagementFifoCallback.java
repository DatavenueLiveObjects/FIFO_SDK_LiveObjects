/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.fifomqtt;

public interface DataManagementFifoCallback {
    void onMessage(int messageId, String message);
}