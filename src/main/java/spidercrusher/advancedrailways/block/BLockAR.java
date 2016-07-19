package spidercrusher.advancedrailways.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spidercrusher.advancedrailways.creativetab.CreativeTab;
import spidercrusher.advancedrailways.tileentity.TileEntityAR;

import java.util.Random;

public class BLockAR extends Block {

    public BLockAR() {
        this(Material.ROCK);
    }

    public BLockAR(Material material) {
        super(material);
        this.setCreativeTab(CreativeTab.SIGNALLING_TAB);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s", getRegistryName());
    }

    // TODO keep these?
    @Override
    public void breakBlock(World world, BlockPos blockPos, IBlockState blockState) {
        dropInventory(world, blockPos);
        super.breakBlock(world, blockPos, blockState);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase entityLiving, ItemStack itemStack) {
        super.onBlockPlacedBy(world, blockPos, blockState, entityLiving, itemStack);

        if (world.getTileEntity(blockPos) instanceof TileEntityAR) {

            if (itemStack.hasDisplayName()) {
                ((TileEntityAR) world.getTileEntity(blockPos)).setCustomName(itemStack.getDisplayName());
            }

            ((TileEntityAR) world.getTileEntity(blockPos)).setFacing(EnumFacing.fromAngle(entityLiving.rotationYaw));
        }
    }

    protected void dropInventory(World world, BlockPos blockPos) {
        TileEntity tileEntity = world.getTileEntity(blockPos);

        if (!(tileEntity instanceof IInventory)) {
            return;
        }

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0) {
                Random rand = new Random();

                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, blockPos.getX() + dX, blockPos.getY() + dY, blockPos.getZ() + dZ, itemStack.copy());

                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
}
