package dev.userteemu.furretmod.rendering;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.util.ResourceLocation;

public class FurretRenderer extends FoxRenderer {
    private static final ResourceLocation FURRET = new ResourceLocation("furretmod", "textures/entity/furret/furret.png");
    private static final ResourceLocation SLEEPING_FURRET = new ResourceLocation("furretmod", "textures/entity/furret/furret_sleep.png");
    private static final ResourceLocation SHINY_FURRET = new ResourceLocation("furretmod", "textures/entity/furret/shiny_furret.png");
    private static final ResourceLocation SLEEPING_SHINY_FURRET = new ResourceLocation("furretmod", "textures/entity/furret/shiny_furret_sleep.png");

    public FurretRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);

        // A new entity model that extends the fox model isn't used because that prevents reusing FoxHeldItemLayer.
        // Instead, needed modifications to the fox model are done here.

        // Texture offsets need to be changed, because front and back legs need different textures. Left and right legs don't need variance.
        entityModel.legBackRight.setTextureOffset(13, 24);
        entityModel.legBackLeft.setTextureOffset(13, 24);
        entityModel.legFrontRight.setTextureOffset(4, 24);
        entityModel.legFrontLeft.setTextureOffset(4, 24);

        // The present cubes use old texture offsets, so they need to be reconstructed
        entityModel.legBackRight.cubeList.clear();
        entityModel.legBackLeft.cubeList.clear();
        entityModel.legFrontRight.cubeList.clear();
        entityModel.legFrontLeft.cubeList.clear();
        entityModel.legBackRight.addBox(2.0F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.001F);
        entityModel.legBackLeft.addBox(2.0F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.001F);
        entityModel.legFrontRight.addBox(2.0F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.001F);
        entityModel.legFrontLeft.addBox(2.0F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.001F);

        // Furret doesn't have a snout
        entityModel.snout.showModel = false;
    }

    @Override
    public ResourceLocation getEntityTexture(FoxEntity entity) {
        if (entity.getVariantType() == FoxEntity.Type.RED) {
            return entity.isSleeping() ? SLEEPING_FURRET : FURRET;
        } else {
            return entity.isSleeping() ? SLEEPING_SHINY_FURRET : SHINY_FURRET;
        }
    }
}
