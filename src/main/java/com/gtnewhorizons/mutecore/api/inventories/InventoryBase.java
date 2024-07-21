package com.gtnewhorizons.mutecore.api.inventories;

import java.util.UUID;

public class InventoryBase {

    // Config related data
    public int maxPerSlot;
    public int maxSlots;

    // Identifiers
    private UUID KEY;
    public String name;

    public InventoryBase(int maxPerSlot, int maxSlots) {
        this.maxPerSlot = maxPerSlot;
        this.maxSlots = maxSlots;
    }

    public UUID getKey() {
        return this.KEY;
    }
}
