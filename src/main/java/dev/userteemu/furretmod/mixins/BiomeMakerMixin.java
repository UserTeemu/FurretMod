package dev.userteemu.furretmod.mixins;

import dev.userteemu.furretmod.FurretMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.biome.MobSpawnInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BiomeMaker.class)
public class BiomeMakerMixin {
    @Inject(method = "makeGenericForestBiome", at = @At("HEAD"))
    private static void addFurretSpawnsInOakForests(float depth, float scale, boolean isFlowerForestVariant, MobSpawnInfo.Builder mobSpawnBuilder, CallbackInfoReturnable<Biome> cir) {
        mobSpawnBuilder.withSpawner(EntityClassification.CREATURE,  new MobSpawnInfo.Spawners(FurretMod.FURRET_ENTITY_TYPE, 8, 2, 4));
    }

    @Inject(method = "makeBirchForestBiome", locals = LocalCapture.CAPTURE_FAILSOFT, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/DefaultBiomeFeatures;withCommonOverworldBlocks(Lnet/minecraft/world/biome/BiomeGenerationSettings$Builder;)V"))
    private static void addFurretSpawnsInBirchForests(float depth, float scale, boolean isTallVariant, CallbackInfoReturnable<Biome> cir, MobSpawnInfo.Builder mobSpawnBuilder, BiomeGenerationSettings.Builder biomeGenerationBuilder) {
        mobSpawnBuilder.withSpawner(EntityClassification.CREATURE,  new MobSpawnInfo.Spawners(FurretMod.FURRET_ENTITY_TYPE, 8, 2, 4));
    }
}
