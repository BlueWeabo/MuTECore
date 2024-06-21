package com.blueweabo.mutecore.api.registry;

import java.lang.ref.WeakReference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.blueweabo.mutecore.MuTECore;
import com.blueweabo.mutecore.api.data.BaseTexture;
import com.blueweabo.mutecore.api.data.WorldStateValidator;
import com.blueweabo.mutecore.api.tile.MultiTileEntity;

import dev.dominion.ecs.api.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class MultiTileContainer {

    private final @Nonnull Class<? extends MultiTileEntity> clazz;
    private final int id;
    private final @Nonnull WeakReference<MultiTileEntityRegistry> reg;
    private final @Nonnull Entity originalEntity;
    private @Nonnull IconContainer baseTexture;
    private @Nonnull IconContainer[][] overlayTextures = new IconContainer[6][];

    public MultiTileContainer(@Nonnull MultiTileEntityRegistry reg, int id, @Nonnull Class<? extends MultiTileEntity> clazz) {
        this.reg = new WeakReference<>(reg);
        this.clazz = clazz;
        this.id = id;
        originalEntity = MuTECore.ENGINE.createEntity(new FakeEntity());
    }

    public @Nonnull MultiTileContainer addComponents(Object... components) {
        for (Object component : components) {
            originalEntity.add(component);
        }
        return this;
    }

    /**
     * Sets the texture path for the entity to use to get its textures from.
     * Uses mutecore as the modid.
     * They are automatically registered.
     */
    public @Nonnull MultiTileContainer texturePath(@Nonnull String path) {
        return texturePath(MuTECore.MODID, path);
    }

    /**
     * Sets the texture path for the entity to use to get its textures from.
     * Uses the modid given from the function to register the icons
     * They are automatically registered.
     */
    public @Nonnull MultiTileContainer texturePath(@Nonnull String modid, @Nonnull String path) {
        baseTexture = new IconContainer(new ResourceLocation(modid, path+"/base").toString());
        return this;
    }

    public boolean register() {
        if (baseTexture != null) TextureRegistry.addBlockIconToRegister(baseTexture);
        return reg.get() != null && reg.get()
            .register(id, this);
    }

    public @Nonnull Entity createNewEntity() {
        Entity newEntity = MuTECore.ENGINE.createEntityAs(originalEntity);
        newEntity.removeType(FakeEntity.class);
        if (baseTexture != null) newEntity.add(new BaseTexture(baseTexture.getIcon()));
        newEntity.add(new Id(id));
        return newEntity;
    }

    public @Nullable MultiTileEntity createNewTileEntity() {
        try {
            MultiTileEntity entity = clazz.newInstance();
            return entity;
        } catch (Exception ex) {
            MuTECore.LOG.error("Unable to create a new TileEntity for class: " + clazz.getName());
            return null;
        }
    }

    public @Nonnull Class<? extends MultiTileEntity> getTileEntityClass() {
        return clazz;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof MultiTileContainer other))
            return false;
        if (id != other.id)
            return false;
        return true;
    }


    public static class FakeEntity {
    }

    public static class Id implements WorldStateValidator {

        private int id;

        public Id(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public void save(NBTTagCompound nbt) {
            NBTTagCompound idNBT = new NBTTagCompound();
            idNBT.setInteger("i", id);
            nbt.setTag("idData", idNBT);
        }

        @Override
        public void load(NBTTagCompound nbt) {
            NBTTagCompound idNBT = nbt.getCompoundTag("idData");
            id = idNBT.getInteger("i");
        }
    }
}
