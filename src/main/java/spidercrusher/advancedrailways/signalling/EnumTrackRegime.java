package spidercrusher.advancedrailways.signalling;

import net.minecraft.util.IStringSerializable;
import spidercrusher.advancedrailways.reference.Messages;
import spidercrusher.advancedrailways.util.Utils;

public enum EnumTrackRegime implements IStringSerializable{
    NORMAL_FLOW(0, "normal_flow", Messages.Signalling.NORMAL_FLOW),
    COUNTER_FLOW (1, "counter_flow", Messages.Signalling.COUNTER_FLOW);

    private final int flowIndex;
    private final String name;
    private final String localisedName;

    EnumTrackRegime(int flowIndex, String name, String unlocalisedName) {
        this.flowIndex = flowIndex;
        this.name = name;
        this.localisedName = Utils.getLocalizedText(unlocalisedName);
    }

    public int getFlowIndex() {
        return flowIndex;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getLocalisedName() {
        return localisedName;
    }

    public static EnumTrackRegime getTrackRegime(int index) {
        return values()[index];
    }

    public EnumTrackRegime change() {
        switch (this) {
            case NORMAL_FLOW:
                return COUNTER_FLOW;
            case COUNTER_FLOW:
                return NORMAL_FLOW;
            default:
                throw new IllegalStateException("Unable to change regime from " + this.getName());
        }
    }
}
