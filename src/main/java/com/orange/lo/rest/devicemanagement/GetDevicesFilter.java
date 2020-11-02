/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.rest.devicemanagement;

public final class GetDevicesFilter {

    private Integer limit;
    private Integer offset;
    private String sort;
    private String id; // regexp
    private String groupPath;
    private String groupId;
    private String name;
    private String tags;
    private String connectors;
    private String interfacesNodeId;
    private Boolean interfacesEnabled;
    private String interfacesStatus;
    private String activityStates;
    private String filterQuery;
    private String fields;

    public GetDevicesFilter withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public GetDevicesFilter withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public GetDevicesFilter withSort(String sort) {
        this.sort = sort;
        return this;
    }

    public GetDevicesFilter withId(String id) {
        this.id = id;
        return this;
    }

    public GetDevicesFilter withGroupPath(String groupPath) {
        this.groupPath = groupPath;
        return this;
    }

    public GetDevicesFilter withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public GetDevicesFilter withName(String name) {
        this.name = name;
        return this;
    }

    public GetDevicesFilter withTags(String tags) {
        this.tags = tags;
        return this;
    }

    public GetDevicesFilter withConnectors(String connectors) {
        this.connectors = connectors;
        return this;
    }

    public GetDevicesFilter withInterfacesNodeId(String interfacesNodeId) {
        this.interfacesNodeId = interfacesNodeId;
        return this;
    }

    public GetDevicesFilter withInterfacesEnabled(Boolean interfacesEnabled) {
        this.interfacesEnabled = interfacesEnabled;
        return this;
    }

    public GetDevicesFilter withInterfacesStatus(String interfacesStatus) {
        this.interfacesStatus = interfacesStatus;
        return this;
    }

    public GetDevicesFilter withActivityStates(String activityStates) {
        this.activityStates = activityStates;
        return this;
    }

    public GetDevicesFilter withFilterQuery(String filterQuery) {
        this.filterQuery = filterQuery;
        return this;
    }

    public GetDevicesFilter withFields(String fields) {
        this.fields = fields;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public String getSort() {
        return sort;
    }

    public String getId() {
        return id;
    }

    public String getGroupPath() {
        return groupPath;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public String getTags() {
        return tags;
    }

    public String getConnectors() {
        return connectors;
    }

    public String getInterfacesNodeId() {
        return interfacesNodeId;
    }

    public Boolean getInterfacesEnabled() {
        return interfacesEnabled;
    }

    public String getInterfacesStatus() {
        return interfacesStatus;
    }

    public String getActivityStates() {
        return activityStates;
    }

    public String getFilterQuery() {
        return filterQuery;
    }

    public String getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "GetDevicesFilter [limit=" + limit + ", offset=" + offset + ", sort=" + sort + ", id=" + id + ", groupPath=" + groupPath + ", groupId=" + groupId + ", name=" + name + ", tags=" + tags + ", connectors=" + connectors + ", interfacesNodeId=" + interfacesNodeId + ", interfacesEnabled=" + interfacesEnabled + ", interfacesStatus=" + interfacesStatus + ", activityStates=" + activityStates + ", filterQuery=" + filterQuery + ", fields=" + fields + "]";
    }
}