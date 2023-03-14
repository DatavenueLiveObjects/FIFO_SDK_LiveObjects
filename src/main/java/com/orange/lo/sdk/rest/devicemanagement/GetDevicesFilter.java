/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.devicemanagement;

import com.orange.lo.sdk.rest.Filter;

import java.util.Objects;

public final class GetDevicesFilter implements Filter{

    private Integer limit;
    private Integer offset;
    private String bookmarkId;
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
    
    public GetDevicesFilter withBookmarkId(String bookmarkId) {
        this.bookmarkId = bookmarkId;
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
    
    public String getBookmarkId() {
        return bookmarkId;
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
        return "GetDevicesFilter [limit=" + limit + ", offset=" + offset + ", bookmarkId=" + bookmarkId + ", sort=" + sort + ", id=" + id + ", groupPath=" + groupPath + ", groupId=" + groupId + ", name=" + name + ", tags=" + tags + ", connectors=" + connectors + ", interfacesNodeId=" + interfacesNodeId + ", interfacesEnabled=" + interfacesEnabled + ", interfacesStatus=" + interfacesStatus + ", activityStates=" + activityStates + ", filterQuery=" + filterQuery + ", fields=" + fields + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetDevicesFilter)) return false;
        GetDevicesFilter that = (GetDevicesFilter) o;
        return Objects.equals(getLimit(), that.getLimit())
                && Objects.equals(getOffset(), that.getOffset())
                && Objects.equals(getBookmarkId(), that.getBookmarkId())
                && Objects.equals(getSort(), that.getSort())
                && Objects.equals(getId(), that.getId())
                && Objects.equals(getGroupPath(), that.getGroupPath())
                && Objects.equals(getGroupId(), that.getGroupId())
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getTags(), that.getTags())
                && Objects.equals(getConnectors(), that.getConnectors())
                && Objects.equals(getInterfacesNodeId(), that.getInterfacesNodeId())
                && Objects.equals(getInterfacesEnabled(), that.getInterfacesEnabled())
                && Objects.equals(getInterfacesStatus(), that.getInterfacesStatus())
                && Objects.equals(getActivityStates(), that.getActivityStates())
                && Objects.equals(getFilterQuery(), that.getFilterQuery())
                && Objects.equals(getFields(), that.getFields());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLimit(), getOffset(), getSort(), getBookmarkId(), getId(), getGroupPath(), getGroupId(), getName(),
                getTags(), getConnectors(), getInterfacesNodeId(), getInterfacesEnabled(), getInterfacesStatus(), getActivityStates(), getFilterQuery(), getFields());
    }
}
