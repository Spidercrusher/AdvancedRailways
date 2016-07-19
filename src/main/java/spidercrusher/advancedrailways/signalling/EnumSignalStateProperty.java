package spidercrusher.advancedrailways.signalling;

import net.minecraft.util.IStringSerializable;

public enum EnumSignalStateProperty implements IStringSerializable{
    OFF(0, "off", false),
    GREEN(1, "green", true),
    DOUBLE_YELLOW(2, "double_yellow", true),
    RED(3, "red", true),
    GREEN_YELLOW_HORIZONTAL(4, "green_yellow_horizontal", true),
    GREEN_YELLOW_VERTICAL(5, "green_yellow_vertical", true),
    RED_WHITE(6, "red_white", true),
    GREEN_BLINK(7, "green_blink", true),
    DOUBLE_YELLOW_BLINK(8, "double_yellow_blink", true),
    RED_BLINK(9, "red_blink", true),
    GREEN_YELLOW_HORIZONTAL_BLINK(10, "green_yellow_horizontal_blink", true),
    GREEN_YELLOW_VERTICAL_BLINK(11, "green_yellow_vertical_blink", true),
    RED_WHITE_BLINK(12, "red_white_blink", true);

    private final int stateIndex;
    private final String name;
    private final boolean shouldGlow;

    EnumSignalStateProperty(int stateIndex, String name, boolean shouldGlow) {
        this.stateIndex = stateIndex;
        this.name = name;
        this.shouldGlow = shouldGlow;
    }

    public int getStateIndex() {
        return stateIndex;
    }

    @Override
    public String getName() {
        return name;
    }

    public static EnumSignalStateProperty getSignalState(int index) {
        return values()[index];
    }

    public boolean shouldGlow() {
        return shouldGlow;
    }
}
