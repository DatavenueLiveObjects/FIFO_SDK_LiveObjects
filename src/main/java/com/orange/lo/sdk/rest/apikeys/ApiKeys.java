package com.orange.lo.sdk.rest.apikeys;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import com.orange.lo.sdk.rest.FilterUtils;
import com.orange.lo.sdk.rest.ResourceClient;
import com.orange.lo.sdk.rest.RestTemplateFactory;
import com.orange.lo.sdk.rest.model.ApiKey;
import com.orange.lo.sdk.rest.model.V0Response;

public class ApiKeys extends ResourceClient{

	public ApiKeys(RestTemplateFactory restTemplateFactory) {
		super(restTemplateFactory);
	}

	private static final String API_KEYS_ENDPOINT = "/v0/apiKeys";
	private static final String API_KEY_ENDPOINT = "/v0/apiKeys/{apiKeyId}";
	
	public ApiKey getApiKey(String apiKeyId) {
		String uri = UriComponentsBuilder.fromUriString(API_KEY_ENDPOINT).buildAndExpand(apiKeyId).toUriString();
		return get(uri, ApiKey.class);
	}
	
	public List<ApiKey> getApiKeys() {
		return getApiKeys(new GetApiKeysFilter());
	}

	public List<ApiKey> getApiKeys(GetApiKeysFilter getApiKeysFilter) {
		String uri = UriComponentsBuilder.fromUriString(API_KEYS_ENDPOINT) //
                .queryParams(FilterUtils.toMap(getApiKeysFilter)) //
                .build().toUriString(); //
		
		ParameterizedTypeReference<V0Response<ApiKey>> typeRef = new ParameterizedTypeReference<V0Response<ApiKey>>() {};
		
		return getV0Many(uri, typeRef);
	}
	
	public void deleteApiKey(String apiKeyId) {
    	String uri = UriComponentsBuilder.fromUriString(API_KEYS_ENDPOINT).pathSegment(apiKeyId) //
    			.build().toUriString();
    	delete(uri);    	
    }
	
	public ApiKey createApiKey(ApiKey apiKey) {
    	return create(API_KEYS_ENDPOINT, apiKey, ApiKey.class);
    }
}
