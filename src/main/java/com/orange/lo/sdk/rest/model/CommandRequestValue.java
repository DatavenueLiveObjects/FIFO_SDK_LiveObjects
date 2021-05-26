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

    public void setReq(String req) {
        this.req = req;
    }

    public Map<String, String> getArg() {
        return arg;
    }

    public void setArg(Map<String, String> arg) {
        this.arg = arg;
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
        if (!(o instanceof CommandRequestValue)) return false;
        CommandRequestValue that = (CommandRequestValue) o;
        return Objects.equals(getReq(), that.getReq()) && Objects.equals(getArg(), that.getArg());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReq(), getArg());
    }
}
