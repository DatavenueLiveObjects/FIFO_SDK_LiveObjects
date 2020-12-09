/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.externalconnector.model;

import java.util.Map;

public class CommandRequestValue {

    private String req;
    private Map<String, String> arg;

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public Map<String, String> getArg() {
        return arg;
    }

    public void setArg(Map<String, String> arg) {
        this.arg = arg;
    }

}
