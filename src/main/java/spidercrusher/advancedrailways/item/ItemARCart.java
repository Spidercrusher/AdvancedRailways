package spidercrusher.advancedrailways.item;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spidercrusher.advancedrailways.carts.CartHelper;
import spidercrusher.advancedrailways.carts.EnumLocomotiveType;
import spidercrusher.advancedrailways.carts.IEnumCartType;
import spidercrusher.advancedrailways.creativetab.CreativeTab;
import spidercrusher.advancedrailways.entity.EntityARCartBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemARCart extends ItemMinecart {

    public static final List<ItemARCart> LOCOMOTIVE_INSTANCES = getInstances(EnumLocomotiveType.ALL_TYPES);

    private static List<ItemARCart> getInstances(IEnumCartType[] cartTypes) {
        List<ItemARCart> list = new ArrayList<ItemARCart>();
        for (IEnumCartType cartType : cartTypes) {
            list.add(cartType.getItemInstance());
        }
        return Collections.unmodifiableList(list);
    }

    private IEnumCartType iEnumCartType;

    public ItemARCart(IEnumCartType iEnumCartType) {
        //noinspection ConstantConditions
        super(EntityMinecart.Type.RIDEABLE);
        this.iEnumCartType = iEnumCartType;
        this.setRegistryName(iEnumCartType.getName());
        this.setMaxDamage(0);
        setHasSubtypes(true);

        this.setCreativeTab(CreativeTab.CARTS_TAB);

        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new BehaviorDefaultDispenseItem());
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos blockPos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = worldIn.getBlockState(blockPos);

        if (!BlockRailBase.isRailBlock(iblockstate)) {
            return EnumActionResult.FAIL;

        } else {
            if (!worldIn.isRemote) {
                BlockRailBase.EnumRailDirection enumRailDirection = iblockstate.getBlock() instanceof BlockRailBase ? iblockstate.getValue(((BlockRailBase)iblockstate.getBlock()).getShapeProperty()) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
                double d0 = 0.0D;

                if (enumRailDirection.isAscending()) {
                    d0 = 0.5D;
                }

                EntityARCartBase entityARCartBase = CartHelper.createCart(worldIn, blockPos.add(0.5D, 0.0625D + d0, 0.5D), iEnumCartType);

                if (stack.hasDisplayName()) {
                    entityARCartBase.setCustomNameTag(stack.getDisplayName());
                }

                worldIn.spawnEntityInWorld(entityARCartBase);
            }

            --stack.stackSize;
            return EnumActionResult.SUCCESS;
        }
    }



    public IEnumCartType getIEnumCartType() {
        return iEnumCartType;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);

        List<String> additionalTooltip = iEnumCartType.getTooltip();
        if (additionalTooltip != null) {
            tooltip.addAll(additionalTooltip);
        }
    }
}
