package spidercrusher.advancedrailways.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import spidercrusher.advancedrailways.network.message.MessageTileEntityAR;
import spidercrusher.advancedrailways.reference.Reference;
import spidercrusher.advancedrailways.util.LogHelper;

public class PacketHandler {

    private static SimpleNetworkWrapper INSTANCE;

    private static int packetID = 0;
    private static final int MAX_PACKET_ID = 255;

    public static void init() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

        INSTANCE.registerMessage(MessageTileEntityAR.Handler.class, MessageTileEntityAR.class, packetID, Side.CLIENT);
    }

    private static int getNextPacketID() {
        if (packetID++ <= MAX_PACKET_ID) {
            return packetID;
        } else {
            throw new IndexOutOfBoundsException("PacketID cannot be higher than " + MAX_PACKET_ID);
        }
    }

    public static SimpleNetworkWrapper getPQNetworkInstance() {
        return INSTANCE;
    }

    public static void sendToServer(IMessage message) {
        PacketHandler.INSTANCE.sendToServer(message);
    }

    /*public static void sendToAllAround(IMessage message, TileEntity tileEntity) {
        sendToAllAround(message, getTargetPointForTileEntity(tileEntity));
    }*/

    public static void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint targetPoint) {
        PacketHandler.INSTANCE.sendToAllAround(message, targetPoint);
    }

    public static void sendToPlayer(IMessage message, EntityPlayer entityPlayer) {
        if (entityPlayer instanceof EntityPlayerMP) {
            PacketHandler.INSTANCE.sendTo(message, (EntityPlayerMP) entityPlayer);
        } else {
            LogHelper.warn("Could not send message to player %s since they are not an instance of EntityPlayerMP");
        }
    }

    /*public static NetworkRegistry.TargetPoint getTargetPointForTileEntity(TileEntity tileEntity) {
        return new NetworkRegistry.TargetPoint(tileEntity.getWorld().provider.getDimension(), tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), Utils.getServerViewDistanceAsBlockCount());
    }*/
}
