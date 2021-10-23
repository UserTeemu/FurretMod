package dev.userteemu.furretmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class FurretEntity extends FoxEntity {
    public FurretEntity(EntityType<? extends FoxEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        try {
            compound.putString("Type", remapFromFoxType(compound.getString("Type")));
        } catch (NullPointerException ignored) {}
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        try {
            compound.putString("Type", remapToFoxType(compound.getString("Type")).getName());
        } catch (NullPointerException ignored) {}
        super.readAdditional(compound);
    }

    public static FoxEntity.Type remapToFoxType(String type) {
        switch (type) {
            case "regular":
                return FoxEntity.Type.RED;
            case "shiny":
                return FoxEntity.Type.SNOW;
            default:
                return null;
        }
    }

    public static String remapFromFoxType(String type) {
        return remapFromFoxType(FoxEntity.Type.getTypeByName(type));
    }

    public static String remapFromFoxType(FoxEntity.Type type) {
        switch (type) {
            case RED:
                return "regular";
            case SNOW:
                return "shiny";
            default:
                return type.name();
        }
    }
}
