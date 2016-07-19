package spidercrusher.advancedrailways.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import spidercrusher.advancedrailways.reference.Names;
import spidercrusher.advancedrailways.tileentity.TileEntitySignal;

public class ModTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntitySignal.class, Names.Blocks.SIGNAL);
    }
}
