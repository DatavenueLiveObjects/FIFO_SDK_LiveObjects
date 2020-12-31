/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FilterUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private FilterUtils() {
    }

    public static MultiValueMap<String, String> toMap(Filter filter) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        Map<String, String> maps = objectMapper.convertValue(filter, new TypeReference<Map<String, String>>() {
        });

        ArrayList<String> removedKeyList = new ArrayList<>();
        maps.forEach((key, value) -> {
            if (value == null) {
                removedKeyList.add(key);
            }
        });
        for (String removedKey : removedKeyList) {
            maps.remove(removedKey);
        }
        parameters.setAll(maps);

        return parameters;
    }
}