package spidercrusher.advancedrailways.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import spidercrusher.advancedrailways.carts.EnumLocomotiveType;
import spidercrusher.advancedrailways.client.model.ModelLocomotive;
import spidercrusher.advancedrailways.client.model.ModelLocomotiveCreative;
import spidercrusher.advancedrailways.client.model.RenderLocomotive;
import spidercrusher.advancedrailways.entity.EntityLocomotive;

public class LocomotiveRenderFactory implements IRenderFactory<EntityLocomotive> {

    public final EnumLocomotiveType enumLocomotiveType;

    public LocomotiveRenderFactory(EnumLocomotiveType enumLocomotiveType) {
        this.enumLocomotiveType = enumLocomotiveType;
    }

    @Override
    public Render<? super EntityLocomotive> createRenderFor(RenderManager manager) {
        ModelLocomotive modelLocomotive;
        switch (enumLocomotiveType) {
            case CREATIVE:
            default:
                modelLocomotive = new ModelLocomotiveCreative(enumLocomotiveType.getName());
                break;
        }
        return new RenderLocomotive(manager, modelLocomotive);
    }
}
