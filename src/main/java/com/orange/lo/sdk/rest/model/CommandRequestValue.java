/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.model;

import java.util.Map;
import java.util.Objects;

public class CommandRequestValue {

    private String req;
    private Map<String, String> arg;

    public CommandRequestValue withReq(String req) {
        this.req = req;
        return this;
    }

    public CommandRequestValue withArg(Map<String, String> arg) {
        this.arg = arg;
        return this;
    }

    public String getReq() {
        return req;
    }

    public Map<String, String> getArg() {
        return arg;
    }

    @Override
    public String toString() {
        return "CommandRequestValue{" +
                "req='" + req + '\'' +
                ", arg=" + arg +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandRequestValue that = (CommandRequestValue) o;
        return Objects.equals(req, that.req) && Objects.equals(arg, that.arg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(req, arg);
    }
}
