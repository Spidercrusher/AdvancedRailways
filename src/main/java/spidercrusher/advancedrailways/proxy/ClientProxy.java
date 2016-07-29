package spidercrusher.advancedrailways.proxy;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import spidercrusher.advancedrailways.carts.EnumLocomotiveType;
import spidercrusher.advancedrailways.client.render.LocomotiveRenderFactory;
import spidercrusher.advancedrailways.entity.EntityLocomotive;
import spidercrusher.advancedrailways.init.ModBlocks;
import spidercrusher.advancedrailways.init.ModItems;
import spidercrusher.advancedrailways.reference.Reference;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderingAndTextures() {
        OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);

        ModItems.initRenderers();
        ModBlocks.initRenderers();

        RenderingRegistry.registerEntityRenderingHandler(EntityLocomotive.class, new LocomotiveRenderFactory(EnumLocomotiveType.CREATIVE));
    }
}
