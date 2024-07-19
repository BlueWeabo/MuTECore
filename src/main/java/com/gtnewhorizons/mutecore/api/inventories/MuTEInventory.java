package com.gtnewhorizons.mutecore.api.inventories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cleanroommc.modularui.utils.fluid.FluidTankLong;
import com.cleanroommc.modularui.utils.item.ItemStackLong;

public class MuTEInventory {

    public InventoryMode mode;

    private List<ItemStackLong> items = new ArrayList<>();
    private List<FluidTankLong> fluids = new ArrayList<>();

    public int maxItemsPerSlot;
    public int maxFluidsPerSlot;

    public int itemSlotAmount;
    public int fluidSlotAmount;

    private final UUID KEY;
    String name;

    public MuTEInventory(int itemSlotAmount, int fluidSlotAmount, int maxItemsPerSlot, int maxFluidsPerSlot) {
        this(InventoryMode.INPUT, "", itemSlotAmount, fluidSlotAmount, maxItemsPerSlot, maxFluidsPerSlot);
    }

    public MuTEInventory(String name, int itemSlotAmount, int fluidSlotAmount, int maxItemsPerSlot,
        int maxFluidsPerSlot) {
        this(InventoryMode.INPUT, name, itemSlotAmount, fluidSlotAmount, maxItemsPerSlot, maxFluidsPerSlot);
    }

    public MuTEInventory(InventoryMode mode, String name, int itemSlotAmount, int fluidSlotAmount, int maxItemsPerSlot,
        int maxFluidsPerSlot) {
        this.mode = mode;
        this.name = name;
        this.KEY = UUID.randomUUID();

        this.itemSlotAmount = itemSlotAmount;
        this.maxItemsPerSlot = maxItemsPerSlot;
        this.fluidSlotAmount = fluidSlotAmount;
        this.maxFluidsPerSlot = maxFluidsPerSlot;
    }

    public List<FluidTankLong> getFluids() {
        return this.fluids;
    }

    public List<ItemStackLong> getItems() {
        return this.items;
    }

    public UUID getKEY() {
        return this.KEY;
    }
}
