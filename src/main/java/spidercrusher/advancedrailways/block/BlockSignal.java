package spidercrusher.advancedrailways.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import spidercrusher.advancedrailways.item.ItemWrench;
import spidercrusher.advancedrailways.reference.Messages;
import spidercrusher.advancedrailways.reference.Names;
import spidercrusher.advancedrailways.signalling.EnumSignalState;
import spidercrusher.advancedrailways.signalling.EnumSignalStateProperty;
import spidercrusher.advancedrailways.signalling.EnumTrackRegime;
import spidercrusher.advancedrailways.tileentity.TileEntitySignal;
import spidercrusher.advancedrailways.util.LogHelper;

import javax.annotation.Nullable;

public class BlockSignal extends BlockTileEntityAR {

    public static final PropertyDirection PROPERTY_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyEnum<EnumTrackRegime> PROPERTY_TRACK_REGIME = PropertyEnum.create("track_regime", EnumTrackRegime.class);
    public static final PropertyEnum<EnumSignalStateProperty> PROPERTY_SIGNAL_STATE = PropertyEnum.create("signal_state", EnumSignalStateProperty.class);

    public static final BlockSignal INSTANCE = new BlockSignal();

    protected static final AxisAlignedBB NORTH_AXIS_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.4D, 0.8D, 1.88D, 0.75D);
    protected static final AxisAlignedBB EAST_AXIS_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.2D, 0.6D, 1.88D, 0.8D);
    protected static final AxisAlignedBB SOUTH_AXIS_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.25D, 0.8D, 1.88D, 0.6D);
    protected static final AxisAlignedBB WEST_AXIS_AABB = new AxisAlignedBB(0.4D, 0.0D, 0.2D, 0.75D, 1.88D, 0.8D);

    private static final int LIGHT_LEVEL_ON = (int) (15.0F * 0.625F);

    BlockSignal() {
        super(Material.IRON);
        this.setRegistryName(Names.Blocks.SIGNAL);
        this.setHardness(1.5f); // TODO check value
        this.setResistance(10.0f); // TODO check value
        this.setSoundType(SoundType.METAL);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PROPERTY_FACING, EnumFacing.SOUTH).withProperty(PROPERTY_TRACK_REGIME, EnumTrackRegime.NORMAL_FLOW).withProperty(PROPERTY_SIGNAL_STATE, EnumSignalStateProperty.OFF));
    }

    @Deprecated
    public int getLightValue(IBlockState state) {
        return state.getValue(PROPERTY_SIGNAL_STATE).shouldGlow() ? 0 : LIGHT_LEVEL_ON;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if ((heldItem != null) && (heldItem.getItem() instanceof ItemWrench)) {
                TileEntity tileEntity = worldIn.getTileEntity(pos);

                if (playerIn.isSneaking()) {
                    EnumTrackRegime newTrackRegime = state.getValue(PROPERTY_TRACK_REGIME).change();
                    worldIn.setBlockState(pos, state.withProperty(PROPERTY_TRACK_REGIME, newTrackRegime), 2);
                    playerIn.addChatComponentMessage(new TextComponentTranslation(Messages.Signalling.TRACK_FLOW_CHANGED, newTrackRegime.getLocalisedName()));

                    if (tileEntity instanceof TileEntitySignal) {
                        ((TileEntitySignal) tileEntity).detectSignallingRail();
                    }
                } else {
                    worldIn.setBlockState(pos, state.withProperty(PROPERTY_FACING, state.getValue(PROPERTY_FACING).rotateY()), 2);

                    if (tileEntity instanceof TileEntitySignal) {
                        ((TileEntitySignal) tileEntity).detectSignallingRail();
                    }
                }

                return true;
            }
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(PROPERTY_FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos blockPos, IBlockState blockState, EntityLivingBase entityLiving, ItemStack itemStack) {
        super.onBlockPlacedBy(worldIn, blockPos, blockState, entityLiving, itemStack);

        worldIn.setBlockState(blockPos, blockState.withProperty(PROPERTY_FACING, entityLiving.getHorizontalFacing()), 2);

        TileEntity tileEntity = worldIn.getTileEntity(blockPos);
        if (tileEntity instanceof TileEntitySignal) {
            ((TileEntitySignal) tileEntity).detectSignallingRail();
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySignal();
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileEntitySignal) {
            return state.withProperty(PROPERTY_SIGNAL_STATE, ((TileEntitySignal) tileEntity).getSignalStateProperty());
        }

        return state;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(PROPERTY_FACING, EnumFacing.getHorizontal(meta % 4)).withProperty(PROPERTY_TRACK_REGIME, EnumTrackRegime.getTrackRegime(meta / 4));
    }

    @Override
    public int getMetaFromState(IBlockState state) { // Values 0-3 are facings for NORMAL_FLOW, values 4-7 are facings for COUNTER_FLOW
        return state.getValue(PROPERTY_FACING).getHorizontalIndex() + (state.getValue(PROPERTY_TRACK_REGIME).getFlowIndex() * 4);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PROPERTY_FACING, PROPERTY_TRACK_REGIME, PROPERTY_SIGNAL_STATE);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch (state.getValue(PROPERTY_FACING)) {
            case NORTH:
                return NORTH_AXIS_AABB;

            case EAST:
                return EAST_AXIS_AABB;

            case SOUTH:
                return SOUTH_AXIS_AABB;

            case WEST:
                return WEST_AXIS_AABB;

            default:
                LogHelper.error("Unknown PROPERTY_FACING BlockState for BlockSignal at %s -- Using NORTH_AXIS_AABB");
                return new AxisAlignedBB(0.2D, 0.0D, 0.4D, 0.8D, 1.88D, 0.75D);
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
