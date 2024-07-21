package com.gtnewhorizons.mutecore.api.inventories;

import com.cleanroommc.modularui.utils.fluid.FluidTankLong;
import com.gtnewhorizons.mutecore.api.data.WorldStateValidator;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryFluid extends InventoryBase implements WorldStateValidator {

    private FluidTankLong[] fluids;

    private final String NBTTAG_KEY = "MuTEFluidInventory";

    public InventoryFluid(int maxPerSlot, int maxSlots) {
        super(maxPerSlot, maxSlots);
        this.fluids = new FluidTankLong[maxSlots];
    }

    @Override
    public void save(NBTTagCompound nbt) {

    }

    @Override
    public void load(NBTTagCompound nbt) {

    }

    public FluidTankLong getFluid(int index) {
        return this.fluids[index];
    }

    public void setFluid(int index, FluidTankLong fluid) {
        this.fluids[index] = fluid;
    }
}
