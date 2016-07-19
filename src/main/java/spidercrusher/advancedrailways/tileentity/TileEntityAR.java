package spidercrusher.advancedrailways.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spidercrusher.advancedrailways.reference.Messages;
import spidercrusher.advancedrailways.reference.Names;
import spidercrusher.advancedrailways.util.Utils;

import java.util.UUID;

public class TileEntityAR extends TileEntity {

    protected EnumFacing facing;
    protected byte state;
    protected String customName;
    protected UUID ownerUUID;

    public TileEntityAR() {
        facing = EnumFacing.SOUTH;
        state = 0;
        customName = "";
        ownerUUID = null;
    }

    public EnumFacing getFacing() {
        return facing;
    }

    public void setFacing(EnumFacing facing) {
        this.facing = facing;
    }

    public short getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public String getOwnerName() {
        if (ownerUUID != null) {
            return Utils.getPlayerNameAsString(ownerUUID);
        }

        return Utils.getLocalizedText(Messages.Common.UNKNOWN_PLAYER);
    }

    public void setOwner(EntityPlayer entityPlayer) {
        this.ownerUUID = entityPlayer.getPersistentID();
    }

    public void setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Names.NBT.FACING)) {
            this.facing = EnumFacing.getFront(nbtTagCompound.getByte(Names.NBT.FACING));
        }

        if (nbtTagCompound.hasKey(Names.NBT.STATE)) {
            this.state = nbtTagCompound.getByte(Names.NBT.STATE);
        }

        if (nbtTagCompound.hasKey(Names.NBT.CUSTOM_NAME)) {
            this.customName = nbtTagCompound.getString(Names.NBT.CUSTOM_NAME);
        }

        if (nbtTagCompound.hasKey(Names.NBT.OWNER_UUID_MOST_SIG) && nbtTagCompound.hasKey(Names.NBT.OWNER_UUID_LEAST_SIG)) {
            this.ownerUUID = new UUID(nbtTagCompound.getLong(Names.NBT.OWNER_UUID_MOST_SIG), nbtTagCompound.getLong(Names.NBT.OWNER_UUID_LEAST_SIG));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound = super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Names.NBT.FACING, (byte) facing.ordinal());
        nbtTagCompound.setByte(Names.NBT.STATE, state);

        if (this.hasCustomName()) {
            nbtTagCompound.setString(Names.NBT.CUSTOM_NAME, customName);
        }

        if (this.hasOwner()) {
            nbtTagCompound.setLong(Names.NBT.OWNER_UUID_MOST_SIG, ownerUUID.getMostSignificantBits());
            nbtTagCompound.setLong(Names.NBT.OWNER_UUID_LEAST_SIG, ownerUUID.getLeastSignificantBits());
        }

        return nbtTagCompound;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    public boolean hasCustomName() {
        return customName != null && customName.length() > 0;
    }

    public boolean hasOwner() {
        return ownerUUID != null;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, -1, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
}
