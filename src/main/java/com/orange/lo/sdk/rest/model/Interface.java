/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Interface {

    @JsonProperty("connector")
    private String connector;

    @JsonProperty("nodeId")
    private String nodeId;

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("status")
    private String status;

    @JsonProperty("definition")
    private Definition definition;

    @JsonProperty("capabilities")
    private Capabilities capabilities;

    @JsonProperty("created")
    private Date created;

    @JsonProperty("updated")
    private Date updated;

    @JsonProperty("lastContact")
    private String lastContact;

    @JsonProperty("activity")
    private Activity activity;

    public Interface withConnector(String connector) {
        this.connector = connector;
        return this;
    }

    public Interface withNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public Interface withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Interface withStatus(String status) {
        this.status = status;
        return this;
    }

    public Interface withDefinition(Definition definition) {
        this.definition = definition;
        return this;
    }

    public Interface withCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
        return this;
    }

    public Interface withCreated(Date created) {
        this.created = created;
        return this;
    }

    public Interface withUpdated(Date updated) {
        this.updated = updated;
        return this;
    }

    public Interface withLastContact(String lastContact) {
        this.lastContact = lastContact;
        return this;
    }

    public Interface withActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public String getConnector() {
        return connector;
    }

    public String getNodeId() {
        return nodeId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getStatus() {
        return status;
    }

    public Definition getDefinition() {
        return definition;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getLastContact() {
        return lastContact;
    }

    public Activity getActivity() {
        return activity;
    }

    @Override
    public String toString() {
        return "Interface [connector=" + connector + ", nodeId=" + nodeId + ", enabled=" + enabled + ", status=" + status + ", definition=" + definition + ", capabilities=" + capabilities + ", created=" + created + ", updated=" + updated + ", lastContact=" + lastContact + ", activity=" + activity + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interface)) return false;
        Interface that = (Interface) o;
        return Objects.equals(getConnector(), that.getConnector())
                && Objects.equals(getNodeId(), that.getNodeId())
                && Objects.equals(getEnabled(), that.getEnabled())
                && Objects.equals(getStatus(), that.getStatus())
                && Objects.equals(getDefinition(), that.getDefinition())
                && Objects.equals(getCapabilities(), that.getCapabilities())
                && Objects.equals(getCreated(), that.getCreated())
                && Objects.equals(getUpdated(), that.getUpdated())
                && Objects.equals(getLastContact(), that.getLastContact())
                && Objects.equals(getActivity(), that.getActivity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConnector(), getNodeId(), getEnabled(), getStatus(), getDefinition(), getCapabilities(),
                getCreated(), getUpdated(), getLastContact(), getActivity());
    }
}
