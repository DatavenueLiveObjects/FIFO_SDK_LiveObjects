package com.orange.lo.sdk.rest.apikeys;

import com.orange.lo.sdk.rest.Filter;

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
}
