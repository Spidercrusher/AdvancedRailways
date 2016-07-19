package spidercrusher.advancedrailways.reference;

public class EntityIDs {

    private static int idCounter = 0;

    private static int getNexId() {
        return ++idCounter;
    }

    public static final int LOCOMOTIVE_CREATIVE = idCounter;
    public static final int LOCOMOTIVE_DIESEL = getNexId();
    public static final int LOCOMOTIVE_ELECTRIC = getNexId();
    public static final int LOCOMOTIVE_HIGH_SPEED = getNexId();
}
