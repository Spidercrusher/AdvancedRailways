package spidercrusher.advancedrailways;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import spidercrusher.advancedrailways.init.*;
import spidercrusher.advancedrailways.network.PacketHandler;
import spidercrusher.advancedrailways.proxy.IProxy;
import spidercrusher.advancedrailways.reference.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.DEPENDENCIES)
public class AdvancedRailways {

    // http://www.minecraftforge.net/wiki/SMP_Coding_Guidelines

    @Mod.Instance(Reference.MOD_ID)
    public static AdvancedRailways instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.init();

        ModItems.init();

        ModBlocks.init();

        ModTileEntities.init();

        ModEntities.init();

        proxy.initRenderingAndTextures();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerEventHandlers();

        Recipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
