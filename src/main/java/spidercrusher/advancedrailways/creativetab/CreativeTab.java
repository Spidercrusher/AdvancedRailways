package spidercrusher.advancedrailways.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import spidercrusher.advancedrailways.reference.Reference;

public class CreativeTab {

    public static final CreativeTabs SIGNALLING_TAB = new CreativeTabs(Reference.MOD_ID + ":signalling") {

        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.RAIL);
        }
    };

    public static final CreativeTabs CARTS_TAB = new CreativeTabs(Reference.MOD_ID + ":carts") {

        @Override
        public Item getTabIconItem() {
            return Items.MINECART;
        }
    };
}
