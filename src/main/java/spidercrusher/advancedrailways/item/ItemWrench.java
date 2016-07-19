package spidercrusher.advancedrailways.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import spidercrusher.advancedrailways.block.BlockSignal;
import spidercrusher.advancedrailways.reference.Names;

public class ItemWrench extends ItemAR {

    public static final ItemWrench INSTANCE = new ItemWrench();

    public ItemWrench() {
        super();
        this.setRegistryName(Names.Items.WRENCH);
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return world.getBlockState(pos).getBlock() instanceof BlockSignal || super.doesSneakBypassUse(stack, world, pos, player);
    }
}
