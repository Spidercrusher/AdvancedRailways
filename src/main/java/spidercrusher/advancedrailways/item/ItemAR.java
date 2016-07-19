package spidercrusher.advancedrailways.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import spidercrusher.advancedrailways.creativetab.CreativeTab;

public class ItemAR extends Item{

    public ItemAR() {
        super();
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTab.SIGNALLING_TAB);
        this.setNoRepair();
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s", getRegistryName());
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return String.format("item.%s", getRegistryName());
    }
}
