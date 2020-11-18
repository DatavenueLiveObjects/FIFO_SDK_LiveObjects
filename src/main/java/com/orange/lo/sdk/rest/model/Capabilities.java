/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Capabilities {

    @JsonProperty("configuration")
    private Configuration configuration;

    @JsonProperty("command")
    private Command command;

    @JsonProperty("resource")
    private Resource resource;

    public Capabilities withConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    public Capabilities withCommand(Command command) {
        this.command = command;
        return this;
    }

    public Capabilities withResource(Resource resource) {
        this.resource = resource;
        return this;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Command getCommand() {
        return command;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public String toString() {
        return "Capabilities [configuration=" + configuration + ", command=" + command + ", resource=" + resource + "]";
    }
}