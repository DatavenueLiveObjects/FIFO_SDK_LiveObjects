/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.rest.devicemanagement;

import com.orange.lo.LOApiClientParameters;
import com.orange.lo.rest.RestTemplateFactoryImpl;

public class DeviceManagement {

    private final Inventory inventory;
    private final Groups groups;

    public DeviceManagement(LOApiClientParameters parameters) {
        RestTemplateFactoryImpl restTemplateFactoryImpl = new RestTemplateFactoryImpl(parameters);
        this.inventory = new Inventory(restTemplateFactoryImpl);
        this.groups = new Groups(restTemplateFactoryImpl);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Groups getGroups() {
        return groups;
    }
}