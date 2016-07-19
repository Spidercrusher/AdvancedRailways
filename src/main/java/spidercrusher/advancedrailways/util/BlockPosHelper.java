package spidercrusher.advancedrailways.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class BlockPosHelper {

    private static final String isEmptySuffix = "IsEmpty";

    public static NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound, String key, BlockPos blockPos) {
        if (blockPos == null) {
            nbtTagCompound.setBoolean(key + isEmptySuffix, true);
            nbtTagCompound.setIntArray(key, new int[]{0, 0, 0});
        } else {
            nbtTagCompound.setBoolean(key + isEmptySuffix, false);
            nbtTagCompound.setIntArray(key, new int[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
        }
        return nbtTagCompound;
    }

    public static BlockPos readFromNBT(NBTTagCompound nbtTagCompound, String key) {
        if (nbtTagCompound.getBoolean(key + isEmptySuffix)) {
            return null;
        } else {
            int[] blockPos = nbtTagCompound.getIntArray(key);
            return new BlockPos(blockPos[0], blockPos[1], blockPos[2]);
        }
    }
}
