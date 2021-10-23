package dev.userteemu.furretmod;

import dev.userteemu.furretmod.entity.FurretEntity;
import dev.userteemu.furretmod.rendering.FurretRenderer;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("furretmod")
public class FurretMod {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final EntityType<FurretEntity> FURRET_ENTITY_TYPE = EntityType.Builder.create(FurretEntity::new, EntityClassification.CREATURE).size(0.6F, 0.7F).trackingRange(8).func_233607_a_(Blocks.SWEET_BERRY_BUSH).build("furretmod:furret");

    public FurretMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(FURRET_ENTITY_TYPE, FurretRenderer::new);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onEntityTypeRegistryEvent(RegistryEvent.Register<EntityType<?>> event) {
            FURRET_ENTITY_TYPE.setRegistryName("furretmod", "furret");
            event.getRegistry().register(FURRET_ENTITY_TYPE);
        }

        @SubscribeEvent
        public static void onItemRegistryEvent(RegistryEvent.Register<Item> event) {
            event.getRegistry().register(new SpawnEggItem(FURRET_ENTITY_TYPE, 16704705, 11108193, (new Item.Properties()).group(ItemGroup.MISC)).setRegistryName("furretmod", "furret_spawn_egg"));
        }

        @SubscribeEvent
        public static void onEntityAttributeCreated(EntityAttributeCreationEvent event) {
            event.put(FURRET_ENTITY_TYPE, FoxEntity.func_234192_eI_().create());
        }
    }
}
