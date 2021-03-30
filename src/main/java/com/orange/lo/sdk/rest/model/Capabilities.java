/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Capabilities {

    @JsonProperty("configuration")
    private Configuration configuration;

    @JsonProperty("command")
    private InterfaceCapability command;

    @JsonProperty("resource")
    private Resource resource;

    public Capabilities withConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    public Capabilities withCommand(InterfaceCapability interfaceCapability) {
        this.command = interfaceCapability;
        return this;
    }

    public Capabilities withResource(Resource resource) {
        this.resource = resource;
        return this;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public InterfaceCapability getCommand() {
        return command;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public String toString() {
        return "Capabilities [configuration=" + configuration + ", command=" + command + ", resource=" + resource + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, configuration, resource);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Capabilities))
            return false;
        Capabilities other = (Capabilities) obj;
        return Objects.equals(command, other.command)
                && Objects.equals(configuration, other.configuration)
                && Objects.equals(resource, other.resource);
    }
}