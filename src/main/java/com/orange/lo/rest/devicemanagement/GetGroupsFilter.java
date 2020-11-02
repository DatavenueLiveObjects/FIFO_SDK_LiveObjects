/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.rest.devicemanagement;

public final class GetGroupsFilter {

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
}