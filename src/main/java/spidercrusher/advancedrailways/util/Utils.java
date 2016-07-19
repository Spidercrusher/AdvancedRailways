package spidercrusher.advancedrailways.util;

import com.google.common.base.Splitter;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.UsernameCache;
import spidercrusher.advancedrailways.reference.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utils {

    public static String firstCharToUpperCase(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static String getLocalizedText(String textReference, Object... args) {
        return I18n.format(textReference, args);
    }

    public static String getPlayerNameAsString(UUID playerUUID) {
        String playerString = UsernameCache.getLastKnownUsername(playerUUID);
        if (playerString == null) {
            LogHelper.warn("Player with UUID " + playerUUID.toString() + " not found. ");
            return getLocalizedText(Messages.Common.UNKNOWN_PLAYER);
        }
        return playerString;
    }

    private static final Splitter lineSplitter = Splitter.on("\n").trimResults();

    public static List<String> getLocalizedTooltip(String unLocalizedTooltip, String... args) {
        String localizedTooltip = Utils.getLocalizedText(unLocalizedTooltip);
        List<String> tooltipList = new ArrayList<String>();
        for (String line : lineSplitter.split(localizedTooltip)) {
            tooltipList.add(line);
        }
        return tooltipList;
    }

    //public static final int CHUNK_SIZE = 16;

    /**
     *
     * @return Render Distance in number of blocks, not in number of chunks
     */
    /*public static int getServerViewDistanceAsBlockCount() {
        return getServerViewDistance() * CHUNK_SIZE;
    }

    public static int getServerViewDistance() {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getViewDistance();
    }

    public static int getPlayerViewDistance() {
        return Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
    }*/
}
