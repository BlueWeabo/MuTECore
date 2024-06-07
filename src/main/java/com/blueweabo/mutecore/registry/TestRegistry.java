package com.blueweabo.mutecore.registry;

import com.blueweabo.mutecore.api.block.MultiTileEntityBlock;
import com.blueweabo.mutecore.api.item.MultiTileEntityItem;
import com.blueweabo.mutecore.api.registry.MultiTileEntityRegistry;
import com.blueweabo.mutecore.api.tile.MultiTileEntity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

public class TestRegistry implements Runnable {

    private static final MultiTileEntityRegistry REGISTRY;
    private static final MultiTileEntityBlock BLOCK;

    static {
        BLOCK = new MultiTileEntityBlock(Material.anvil);
        GameRegistry.registerBlock(BLOCK, MultiTileEntityItem.class, "mutecore.testtiles");
        REGISTRY = new MultiTileEntityRegistry(BLOCK);
    }
    @Override
    public void run() {
        REGISTRY.create(0, MultiTileEntity.class);
    }
}
