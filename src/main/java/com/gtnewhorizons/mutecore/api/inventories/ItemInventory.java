package com.gtnewhorizons.mutecore.api.inventories;

import com.cleanroommc.modularui.utils.item.ItemStackLong;
import com.gtnewhorizons.mutecore.api.data.WorldStateValidator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemInventory extends InventoryBase implements WorldStateValidator {

    private ItemStackLong[] items;

    private final String NBTTAG_KEY = "MuTEItemInventory";

    public ItemInventory(int maxPerSlot, int maxSlots) {
        super(maxPerSlot, maxSlots);
        this.items = new ItemStackLong[maxSlots];
    }

    @Override
    public void save(NBTTagCompound nbt) {
        // no clue if this works, idea was to shove items under its
        // own NBT compound tag for easier read when/if needed

        // even less of a clue what to name these variables
        NBTTagCompound inv = nbt.getCompoundTag(NBTTAG_KEY);
        NBTTagList itemList = new NBTTagList();

        for (ItemStackLong item : items) {
            NBTTagCompound entry = new NBTTagCompound();
            item.writeToNBT(entry);
            itemList.appendTag(entry);
        }

        inv.setTag("Items", itemList);
        inv.setString("UUID", this.getKey().toString());
    }

    @Override
    public void load(NBTTagCompound nbt) {
        // for (???) { items.add(ItemStackLong.loadFromNBT(???)); }
    }

    public ItemStackLong getItem(int index) {
        return this.items[index];
    }

    public void setItem(int index, ItemStackLong item) {
        this.items[index] = item;
    }
}
