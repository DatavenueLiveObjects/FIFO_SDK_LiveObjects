/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.devicemanagement;

import com.orange.lo.sdk.rest.RestTemplateFactoryImpl;

public class DeviceManagement {

    private final Inventory inventory;
    private final Groups groups;
    private final Commands commands;

    public DeviceManagement(RestTemplateFactoryImpl restTemplateFactoryImpl) {
        this.inventory = new Inventory(restTemplateFactoryImpl);
        this.groups = new Groups(restTemplateFactoryImpl);
        this.commands = new Commands(restTemplateFactoryImpl);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Groups getGroups() {
        return groups;
    }

    public Commands getCommands() {
        return commands;
    }
}