/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.devicemanagement;

import com.orange.lo.sdk.rest.Filter;

import java.util.Objects;

public final class GetGroupsFilter implements Filter{

    private Integer limit;
    private Integer offset;
    private String parentId;

    public GetGroupsFilter withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public GetGroupsFilter withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public GetGroupsFilter withParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public String getParentId() {
        return parentId;
    }

    @Override
    public String toString() {
        return "GetGroupsFilter [limit=" + limit + ", offset=" + offset + ", parentId=" + parentId + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetGroupsFilter)) return false;
        GetGroupsFilter that = (GetGroupsFilter) o;
        return Objects.equals(getLimit(), that.getLimit())
                && Objects.equals(getOffset(), that.getOffset())
                && Objects.equals(getParentId(), that.getParentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLimit(), getOffset(), getParentId());
    }
}
