package spidercrusher.advancedrailways.client.model;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import spidercrusher.advancedrailways.carts.EnumLocomotiveType;
import spidercrusher.advancedrailways.client.render.RenderHelper;
import spidercrusher.advancedrailways.entity.EntityLocomotive;
import spidercrusher.advancedrailways.reference.Models;

public class RenderLocomotive extends Render<EntityLocomotive> {

    protected ModelLocomotive modelLocomotive;

    public RenderLocomotive(RenderManager renderManager, EnumLocomotiveType enumLocomotiveType) {
        super(renderManager);
        this.modelLocomotive = new ModelLocomotive(RenderHelper.loadObjModel(new ResourceLocation(Models.LOCOMOTIVE_BASE_LOCATION + enumLocomotiveType.getName() + Models.OBJ_MODEL_FILE_EXTENTION)));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLocomotive entity) {
        return null;
    }

    @Override
    public void doRender(EntityLocomotive entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

    }
}
