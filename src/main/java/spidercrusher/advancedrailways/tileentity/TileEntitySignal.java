package spidercrusher.advancedrailways.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import spidercrusher.advancedrailways.block.BlockSignal;
import spidercrusher.advancedrailways.reference.Names;
import spidercrusher.advancedrailways.signalling.EnumSignalState;
import spidercrusher.advancedrailways.signalling.EnumSignalStateProperty;
import spidercrusher.advancedrailways.signalling.EnumTrackRegime;
import spidercrusher.advancedrailways.util.BlockPosHelper;
import spidercrusher.advancedrailways.util.LogHelper;

public class TileEntitySignal extends TileEntityAR {

    private EnumSignalState signalState;
    private BlockPos linkedSignallingRailPos;

    public TileEntitySignal() {
        super();
        signalState = EnumSignalState.OFF;
        linkedSignallingRailPos = null;
    }

    public EnumSignalState getSignalState() {
        return signalState;
    }

    public EnumSignalStateProperty getSignalStateProperty() {
        return signalState.getProperty(worldObj.getBlockState(pos).getValue(BlockSignal.PROPERTY_TRACK_REGIME) == EnumTrackRegime.COUNTER_FLOW);
    }

    public void setSignalState(EnumSignalState signalState) {
        if (!getWorld().isRemote) {
            this.signalState = signalState;
            IBlockState iBlockState = getWorld().getBlockState(getPos()).getActualState(getWorld(), getPos());
            getWorld().setBlockState(getPos(), iBlockState, 3);
            getWorld().notifyBlockUpdate(pos, iBlockState, iBlockState, 3);
        }
    }

    public void setLinkedSignallingRailPos(BlockPos linkedSignallingRailPos) {
        if (getWorld().getTileEntity(linkedSignallingRailPos) instanceof TileEntitySignallingRail) {
            this.linkedSignallingRailPos = linkedSignallingRailPos;
        }
    }

    public void clearLinkedSignallingRailPos() {
        this.linkedSignallingRailPos = null;
    }

    /**
     * 1: normal flow/counter flow right side
     * 2: normal flow/counter flow wrong side
     */

    public void detectSignallingRail() {
        if (!getWorld().isRemote) {
            LogHelper.info("detecting Signal");
            IBlockState iBlockState = getWorld().getBlockState(getPos());
            EnumFacing facing = iBlockState.getValue(BlockSignal.PROPERTY_FACING).getOpposite();
            BlockPos searchPos = new BlockPos(getPos());

            searchPos = searchPos.offset(facing, 3);
            BlockPos searchPosLeft = searchPos.offset(facing.rotateYCCW());
            BlockPos searchPosRight = searchPos.offset(facing.rotateY());
            LogHelper.info(searchPosLeft + " - " + searchPosRight);

            if (getWorld().getTileEntity(searchPosLeft) instanceof TileEntitySignallingRail) {
                //noinspection ConstantConditions
                //((TileEntitySignallingRail) getWorld().getTileEntity(searchPosLeft)).detectSignals();

                LogHelper.info("found SignallingRail at pos %s", searchPosLeft.toString());

            } else if (getWorld().getTileEntity(searchPosRight) instanceof TileEntitySignallingRail) {
                //noinspection ConstantConditions
                //((TileEntitySignallingRail) getWorld().getTileEntity(searchPosRight)).detectSignals();

                LogHelper.info("found SignallingRail at pos %s", searchPosRight.toString());
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Names.NBT.SIGNAL_STATE)) {
            this.signalState = EnumSignalState.getSignalState(nbtTagCompound.getByte(Names.NBT.SIGNAL_STATE));
        }

        if (nbtTagCompound.hasKey(Names.NBT.SIGNALLING_RAIL_POS)) {
            this.linkedSignallingRailPos = BlockPosHelper.readFromNBT(nbtTagCompound, Names.NBT.SIGNALLING_RAIL_POS);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound = super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Names.NBT.SIGNAL_STATE, (byte) signalState.getStateIndex());

        nbtTagCompound = BlockPosHelper.writeToNBT(nbtTagCompound, Names.NBT.SIGNALLING_RAIL_POS, linkedSignallingRailPos);

        return nbtTagCompound;
    }
}
