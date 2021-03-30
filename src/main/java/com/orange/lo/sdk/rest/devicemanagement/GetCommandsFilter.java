/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.devicemanagement;

import com.orange.lo.sdk.rest.Filter;

import java.util.List;
import java.util.Objects;

public final class GetCommandsFilter implements Filter {

    private String from;
    private Integer limit;
    private Integer offset;
    private List<String> sort;
    private String to;

    public GetCommandsFilter withFrom(String from) {
        this.from = from;
        return this;
    }

    public GetCommandsFilter withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public GetCommandsFilter withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public GetCommandsFilter withSort(List<String> sort) {
        this.sort = sort;
        return this;
    }

    public GetCommandsFilter withTo(String to) {
        this.to = to;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public List<String> getSort() {
        return sort;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "GetCommandsFilter{" +
                "from='" + from + '\'' +
                ", limit=" + limit +
                ", offset=" + offset +
                ", sort=" + sort +
                ", to='" + to + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetCommandsFilter that = (GetCommandsFilter) o;
        return Objects.equals(from, that.from)
                && Objects.equals(limit, that.limit)
                && Objects.equals(offset, that.offset)
                && Objects.equals(sort, that.sort)
                && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, limit, offset, sort, to);
    }
}