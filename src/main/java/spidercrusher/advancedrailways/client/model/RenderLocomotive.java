package spidercrusher.advancedrailways.client.model;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.util.ResourceLocation;
import spidercrusher.advancedrailways.entity.EntityLocomotive;

public class RenderLocomotive extends RenderMinecart<EntityLocomotive> {

    public RenderLocomotive(RenderManager renderManager, ModelLocomotive modelLocomotive) {
        super(renderManager);
        this.modelMinecart = modelLocomotive;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLocomotive entity) {
        //return new ResourceLocation("textures/blocks/dirt.png");
        return entity.getLocomotiveType().getTextureResourceLocation();
    }

    @Override
    public void doRender(EntityLocomotive entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
