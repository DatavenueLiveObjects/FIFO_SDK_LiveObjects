/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Device {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("tags")
    private List<String> tags = new ArrayList<>();

    @JsonProperty("properties")
    private Map<String, String> properties;

    @JsonProperty("group")
    private Group group;

    @JsonProperty("interfaces")
    private List<Interface> interfaces = new ArrayList<>();

    @JsonProperty("defaultDataStreamId")
    private String defaultDataStreamId;

    @JsonProperty("created")
    private Date created;

    @JsonProperty("updated")
    private Date updated;

    @JsonProperty("activityState")
    private String activityState;

    public Device withId(String id) {
        this.id = id;
        return this;
    }

    public Device withName(String name) {
        this.name = name;
        return this;
    }

    public Device withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Device withProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public Device withGroup(Group group) {
        this.group = group;
        return this;
    }

    public Device withInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    public Device withDefaultDataStreamId(String defaultDataStreamId) {
        this.defaultDataStreamId = defaultDataStreamId;
        return this;
    }

    public Device withCreated(Date created) {
        this.created = created;
        return this;
    }

    public Device withUpdated(Date updated) {
        this.updated = updated;
        return this;
    }

    public Device withActivityState(String activityState) {
        this.activityState = activityState;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getTags() {
        return tags;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public Group getGroup() {
        return group;
    }

    public List<Interface> getInterfaces() {
        return interfaces;
    }

    public String getDefaultDataStreamId() {
        return defaultDataStreamId;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getActivityState() {
        return activityState;
    }

    @Override
    public String toString() {
        return "Device [id=" + id + ", name=" + name + ", tags=" + tags + ", properties=" + properties + ", group=" + group + ", interfaces=" + interfaces + ", defaultDataStreamId=" + defaultDataStreamId + ", created=" + created + ", updated=" + updated + ", activityState=" + activityState + "]";
    }
}