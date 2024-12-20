package com.gtnewhorizons.mutecore.api.inventory;

import net.minecraftforge.fluids.Fluid;

import com.cleanroommc.modularui.utils.fluid.FluidTankLong;
import com.cleanroommc.modularui.utils.fluid.IFluidTankLong;
import com.cleanroommc.modularui.utils.fluid.IFluidTanksHandler;

public class FluidComponentInventoryHandler implements IFluidTanksHandler {

    private FluidInventory inventory;

    public FluidComponentInventoryHandler(FluidInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public int getTanks() {
        return inventory.getSize();
    }

    @Override
    public IFluidTankLong getTank(int tankSlot) {
        return inventory.get(tankSlot);
    }

    @Override
    public void setFluidInTank(int tankSlot, Fluid fluid, long amount) {
        inventory.set(tankSlot, new FluidTankLong(fluid, amount, getTank(tankSlot).getCapacityLong()));
    }
}
