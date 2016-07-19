package spidercrusher.advancedrailways.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import spidercrusher.advancedrailways.block.BlockSignal;
import spidercrusher.advancedrailways.block.BlockSignallingRail;

public class ModBlocks {

    public static void init() {
        registerBlock(BlockSignal.INSTANCE);
        registerBlock(BlockSignallingRail.INSTANCE);
    }

    private static void registerBlock(Block block) {
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    public static void initRenderers() {
        registerObjItemRenderer(BlockSignal.INSTANCE);
        registerObjItemRenderer(BlockSignallingRail.INSTANCE);
    }

    @SideOnly(Side.CLIENT)
    private static void registerObjItemRenderer(Block block) {
        Item item = Item.getItemFromBlock(block);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
