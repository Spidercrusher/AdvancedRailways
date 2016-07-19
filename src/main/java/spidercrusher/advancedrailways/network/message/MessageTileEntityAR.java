package spidercrusher.advancedrailways.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import spidercrusher.advancedrailways.tileentity.TileEntityAR;

import java.util.UUID;

public class MessageTileEntityAR implements IMessage {

    public BlockPos blockPos;
    public EnumFacing facing;
    public byte state;
    public String customName;
    public UUID ownerUUID;

    public MessageTileEntityAR() {

    }

    public MessageTileEntityAR(TileEntityAR tileEntityAR) {
        this.blockPos = tileEntityAR.getPos();
        this.facing = tileEntityAR.getFacing();
        this.state = (byte) tileEntityAR.getState();
        this.customName = tileEntityAR.getCustomName();
        this.ownerUUID = tileEntityAR.getOwnerUUID();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.facing = EnumFacing.getFront(buf.readByte());
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        if (buf.readBoolean()) {
            this.ownerUUID = new UUID(buf.readLong(), buf.readLong());
        } else {
            this.ownerUUID = null;
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(blockPos.getX());
        buf.writeInt(blockPos.getY());
        buf.writeInt(blockPos.getZ());
        buf.writeByte(facing.ordinal());
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        if (ownerUUID != null) {
            buf.writeBoolean(true);
            buf.writeLong(ownerUUID.getMostSignificantBits());
            buf.writeLong(ownerUUID.getLeastSignificantBits());
        } else {
            buf.writeBoolean(false);
        }
    }

    public static class Handler implements IMessageHandler<MessageTileEntityAR, IMessage> {

        @Override
        public IMessage onMessage(final MessageTileEntityAR message, final MessageContext messageContext) {
            IThreadListener mainThread = Minecraft.getMinecraft();
            mainThread.addScheduledTask(new Runnable() {

                @Override
                public void run() {
                    TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.blockPos);

                    if (tileEntity instanceof TileEntityAR) {
                        TileEntityAR tileEntityPQ = (TileEntityAR) tileEntity;

                        tileEntityPQ.setFacing(message.facing);
                        tileEntityPQ.setState(message.state);
                        tileEntityPQ.setCustomName(message.customName);
                        tileEntityPQ.setOwnerUUID(message.ownerUUID);
                    }

                }
            });
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("MessageTileEntityAR - x: %s, y: %s, z: %s, facing: %s, state: %s, customName: %s, ownerUUID: %s", blockPos.getX(), blockPos.getY(), blockPos.getZ(), facing.getName(), state, customName, ownerUUID);
    }
}
