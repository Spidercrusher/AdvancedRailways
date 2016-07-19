package spidercrusher.advancedrailways.block;

import net.minecraft.block.BlockRailDetector;
import spidercrusher.advancedrailways.creativetab.CreativeTab;
import spidercrusher.advancedrailways.reference.Names;

public class BlockSignallingRail extends BlockRailDetector {

    public static final BlockSignallingRail INSTANCE = new BlockSignallingRail();

    public BlockSignallingRail() {
        super();
        this.setRegistryName(Names.Blocks.SIGNALLING_RAIL);
        this.setCreativeTab(CreativeTab.SIGNALLING_TAB);
    }

}
