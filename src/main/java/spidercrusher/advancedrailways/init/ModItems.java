package spidercrusher.advancedrailways.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import spidercrusher.advancedrailways.item.ItemARCart;
import spidercrusher.advancedrailways.item.ItemWalkieTalkie;
import spidercrusher.advancedrailways.item.ItemWrench;

import java.util.List;

public class ModItems {

    public static void init() {
        registerItem(ItemWrench.INSTANCE);
        registerItem(ItemWalkieTalkie.INSTANCE);

        for (ItemARCart instance : ItemARCart.LOCOMOTIVE_INSTANCES) {
            registerItem(instance);
        }
    }

    private static void registerItem(Item item) {
        GameRegistry.register(item);
    }

    @SideOnly(Side.CLIENT)
    public static void initRenderers() {
        registerItemRenderer(ItemWrench.INSTANCE);
        registerItemRenderer(ItemWalkieTalkie.INSTANCE);

        registerMultipleItemRenderers(ItemARCart.LOCOMOTIVE_INSTANCES);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemRenderer(Item item) {
        registerItemRenderer(item, 0);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemRenderer(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    private static void registerMultipleItemRenderers(List<? extends Item> items) {
        for (int meta = 0; meta < items.size(); meta++) {
            registerItemRenderer(items.get(meta), meta);
        }
    }
}
