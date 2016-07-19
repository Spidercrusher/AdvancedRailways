package spidercrusher.advancedrailways.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spidercrusher.advancedrailways.reference.Names;
import spidercrusher.advancedrailways.signalling.EnumSignalState;
import spidercrusher.advancedrailways.tileentity.TileEntitySignal;
import spidercrusher.advancedrailways.util.LogHelper;

public class ItemWalkieTalkie extends ItemAR {

    public static final ItemWalkieTalkie INSTANCE = new ItemWalkieTalkie();

    public ItemWalkieTalkie() {
        super();
        this.setRegistryName(Names.Items.WALKIE_TALKIE);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileEntitySignal) {
                if (playerIn.isSneaking()) {
                    LogHelper.info(((TileEntitySignal) tileEntity).getSignalState().getName());

                } else {
                    EnumSignalState newSignalState = ((TileEntitySignal) tileEntity).getSignalState().cycleSignalStates();
                    LogHelper.info("changingSignal to %s", newSignalState);
                    ((TileEntitySignal) tileEntity).setSignalState(newSignalState);
                }
            }
        } else if (playerIn.isSneaking()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileEntitySignal) {
                LogHelper.info(((TileEntitySignal) tileEntity).getSignalState().getName());
            }
        }

        return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
