package spidercrusher.advancedrailways.carts;

public enum EnumSpeed {
    STOP(0),
    SHUNTING_SPEED(1),
    SLOW(2),
    NORMAL(4),
    FAST(6),
    HIGH_SPEED(8),
    HIGH_SPEED_FAST(10);

    private int speedMultiplier;

    EnumSpeed(int speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    public int getSpeedMultiplier() {
        return speedMultiplier;
    }
}
