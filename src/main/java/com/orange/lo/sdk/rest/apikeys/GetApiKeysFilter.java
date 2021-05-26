package com.orange.lo.sdk.rest.apikeys;

import com.orange.lo.sdk.rest.Filter;

import java.util.Objects;

public class GetApiKeysFilter implements Filter{

	private Integer page;
	private Integer size;
    
    public GetApiKeysFilter withPage(Integer page) {
        this.page = page;
        return this;
    }

    public GetApiKeysFilter withSize(Integer size) {
        this.size = size;
        return this;
    }

	public Integer getPage() {
		return page;
	}

	public Integer getSize() {
		return size;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetApiKeysFilter)) return false;
        GetApiKeysFilter that = (GetApiKeysFilter) o;
        return Objects.equals(getPage(), that.getPage())
                && Objects.equals(getSize(), that.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPage(), getSize());
    }
}
