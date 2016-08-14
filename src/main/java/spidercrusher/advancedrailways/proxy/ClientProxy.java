package spidercrusher.advancedrailways.proxy;

import net.minecraftforge.client.model.obj.OBJLoader;
import spidercrusher.advancedrailways.init.ModBlocks;
import spidercrusher.advancedrailways.init.ModEntities;
import spidercrusher.advancedrailways.init.ModItems;
import spidercrusher.advancedrailways.reference.Reference;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderingAndTextures() {

        OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);

        ModItems.initRenderers();
        ModBlocks.initRenderers();
        ModEntities.initRenderers();
    }
}
