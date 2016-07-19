package spidercrusher.advancedrailways.tileentity;

import net.minecraft.block.BlockRailDetector;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import spidercrusher.advancedrailways.block.BlockSignal;
import spidercrusher.advancedrailways.signalling.EnumTrackRegime;
import spidercrusher.advancedrailways.util.LogHelper;

public class TileEntitySignallingRail extends TileEntityAR {

    private BlockPos signal1Pos = null;
    private BlockPos signal2Pos = null;

    TileEntitySignallingRail() {
        super();
    }

    /**
     * Start searching at E or N, depending on orientation
     *
     * Can have 1 or 2 normal flow signals
     * Can only have 1 counter flow signal
     * wrong side is only checked if no right side signals are detected
     *
     * priority order:
     * 1: normal flow right side (direction 1)
     * 2: if normal flow found, check corresponding counter flow
     * 3: normal flow right side (direction 2)
     * 4: if normal flow found, check corresponding counter flow
     *
     * 5: normal flow wrong side (direction 1)
     * 6: if normal flow found, check corresponding counter flow
     * 7: normal flow wrong side (direction 2)
     * 8: if normal flow found, check corresponding counter flow
     */

    public void detectSignals() {
        IBlockState iBlockState = getWorld().getBlockState(getPos());
        EnumFacing enumFacing;

        switch (iBlockState.getValue(BlockRailDetector.SHAPE)) {
            default:
            case NORTH_SOUTH:
            case ASCENDING_NORTH:
            case ASCENDING_SOUTH:
                enumFacing = EnumFacing.NORTH;
                break;
            case EAST_WEST:
            case ASCENDING_EAST:
            case ASCENDING_WEST:
                enumFacing = EnumFacing.EAST;
                break;
        }

        signal1Pos = null;
        signal2Pos = null;
        boolean found2Signals = false;

        TileEntitySignal tileEntitySignal = searchDirection(enumFacing, true, EnumTrackRegime.NORMAL_FLOW);
        if (tileEntitySignal != null) {
            signal1Pos = tileEntitySignal.getPos();

            tileEntitySignal = searchDirection(enumFacing.getOpposite(), false, EnumTrackRegime.COUNTER_FLOW);
            if (tileEntitySignal != null) {
                signal2Pos = tileEntitySignal.getPos();
                found2Signals = true;
            }
        }

        tileEntitySignal = searchDirection(enumFacing.getOpposite(), true, EnumTrackRegime.NORMAL_FLOW);
        if (!found2Signals && (tileEntitySignal != null)) {
            if (signal1Pos == null) {
                signal1Pos = tileEntitySignal.getPos();
            } else {
                signal2Pos = tileEntitySignal.getPos();
                found2Signals = true;
            }

            tileEntitySignal = searchDirection(enumFacing, false, EnumTrackRegime.NORMAL_FLOW);
            if (!found2Signals && (tileEntitySignal != null)) {
                signal2Pos = tileEntitySignal.getPos();
                found2Signals = true;
            }
        }

        if ((signal1Pos == null) && (signal2Pos == null)) {
            tileEntitySignal = searchDirection(enumFacing, false, EnumTrackRegime.NORMAL_FLOW);
            if (tileEntitySignal != null) {
                signal1Pos = tileEntitySignal.getPos();

                tileEntitySignal = searchDirection(enumFacing.getOpposite(), true, EnumTrackRegime.COUNTER_FLOW);
                if (tileEntitySignal != null) {
                    signal2Pos = tileEntitySignal.getPos();
                    found2Signals = true;
                }
            }

            tileEntitySignal = searchDirection(enumFacing.getOpposite(), false, EnumTrackRegime.NORMAL_FLOW);
            if (!found2Signals && (tileEntitySignal != null)) {
                if (signal1Pos == null) {
                    signal1Pos = tileEntitySignal.getPos();
                } else {
                    signal2Pos = tileEntitySignal.getPos();
                    found2Signals = true;
                }

                tileEntitySignal = searchDirection(enumFacing, true, EnumTrackRegime.NORMAL_FLOW);
                if (!found2Signals && (tileEntitySignal != null)) {
                    signal2Pos = tileEntitySignal.getPos();
                }
            }
        }

        if (signal1Pos != null) {
            LogHelper.info("setting signalPos1");
            //noinspection ConstantConditions
            ((TileEntitySignal) getWorld().getTileEntity(signal1Pos)).setLinkedSignallingRailPos(getPos());
        }
        if (signal2Pos != null) {
            LogHelper.info("setting signalPos2");
            //noinspection ConstantConditions
            ((TileEntitySignal) getWorld().getTileEntity(signal2Pos)).setLinkedSignallingRailPos(getPos());
        }
    }

    /**
     *
     * @param enumFacing direction to start searching (and the signal has to face)
     * @param checkLeftOfRails the which side of the rail has to be checked, either left (true) or right (false) when looking in the direction of enumFacing
     * @param trackRegime the track regime of the signal searching for
     * @return TileEntitySignal that has been found, or null if none has been found
     */

    private TileEntitySignal searchDirection(EnumFacing enumFacing, boolean checkLeftOfRails, EnumTrackRegime trackRegime) {
        BlockPos searchPos = new BlockPos(getPos()).offset(enumFacing, 3);

        if (checkLeftOfRails) {
            searchPos = searchPos.offset(enumFacing.rotateYCCW());
        } else {
            searchPos = searchPos.offset(enumFacing.rotateY());
        }

        TileEntity tileEntity = getWorld().getTileEntity(searchPos);
        IBlockState blockState = getWorld().getBlockState(searchPos);
        if ((tileEntity instanceof TileEntitySignal) && (blockState.getValue(BlockSignal.PROPERTY_FACING) == enumFacing) && (blockState.getValue(BlockSignal.PROPERTY_TRACK_REGIME) == trackRegime)) {
            return (TileEntitySignal) tileEntity;
        }
        return null;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound = super.writeToNBT(nbtTagCompound);

        return nbtTagCompound;
    }
}
