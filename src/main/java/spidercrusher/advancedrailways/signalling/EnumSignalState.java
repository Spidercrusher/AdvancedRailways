package spidercrusher.advancedrailways.signalling;

import net.minecraft.util.IStringSerializable;

public enum EnumSignalState implements IStringSerializable {
    OFF(0, "off", EnumSignalStateProperty.OFF, EnumSignalStateProperty.OFF),
    GREEN(1, "green", EnumSignalStateProperty.GREEN, EnumSignalStateProperty.GREEN_BLINK),
    DOUBLE_YELLOW(2, "double_yellow", EnumSignalStateProperty.DOUBLE_YELLOW, EnumSignalStateProperty.DOUBLE_YELLOW_BLINK),
    RED(3, "red", EnumSignalStateProperty.RED, EnumSignalStateProperty.RED_BLINK),
    GREEN_YELLOW_HORIZONTAL(4, "green_yellow_horizontal", EnumSignalStateProperty.GREEN_YELLOW_HORIZONTAL, EnumSignalStateProperty.GREEN_YELLOW_HORIZONTAL_BLINK),
    GREEN_YELLOW_VERTICAL(5, "green_yellow_vertical", EnumSignalStateProperty.GREEN_YELLOW_VERTICAL, EnumSignalStateProperty.GREEN_YELLOW_VERTICAL_BLINK),
    RED_WHITE(6, "red_white", EnumSignalStateProperty.RED_WHITE, EnumSignalStateProperty.RED_WHITE_BLINK);

    private final int stateIndex;
    private final String name;
    private final EnumSignalStateProperty normalState;
    private final EnumSignalStateProperty blinkState;

    EnumSignalState(int stateIndex, String name, EnumSignalStateProperty normalState, EnumSignalStateProperty blinkState) {
        this.stateIndex = stateIndex;
        this.name = name;
        this.normalState = normalState;
        this.blinkState = blinkState;
    }

    public int getStateIndex() {
        return stateIndex;
    }

    @Override
    public String getName() {
        return name;
    }

    public EnumSignalStateProperty getProperty(boolean shouldBlink) {
        if(shouldBlink) {
            return blinkState;
        } else {
            return normalState;
        }
    }

    public static EnumSignalState getSignalState(int index) {
        return values()[index];
    }

    public EnumSignalState cycleSignalStates() {
        switch (this) {
            case OFF:
                return GREEN;
            case GREEN:
                return DOUBLE_YELLOW;
            case DOUBLE_YELLOW:
                return RED;
            case RED:
                return GREEN_YELLOW_HORIZONTAL;
            case GREEN_YELLOW_HORIZONTAL:
                return GREEN_YELLOW_VERTICAL;
            case GREEN_YELLOW_VERTICAL:
                return RED_WHITE;
            case RED_WHITE:
                return OFF;
            default:
                throw new IllegalStateException("Unable to change signal state from " + this.getName());
        }
    }
}
