package spidercrusher.advancedrailways.reference;

public class Messages {

    public static final class Common {
        private static final String COMMON_PREFIX = "common." + Reference.MOD_ID + ":";

        public static final String UNKNOWN_PLAYER = COMMON_PREFIX + "unknownPlayer";
    }

    public static final class Signalling {
        private static final String SIGNALLING_PREFIX = "signalling." + Reference.MOD_ID + ":";

        public static final String TRACK_FLOW_CHANGED = SIGNALLING_PREFIX + "trackFlowChanged";
        public static final String NORMAL_FLOW = SIGNALLING_PREFIX + "normalFlow";
        public static final String COUNTER_FLOW = SIGNALLING_PREFIX + "counterFlow";
    }

    public static final class Tooltip {
        public static final String TOOLTIP_PREFIX = "tooltip." + Reference.MOD_ID + ":";

        public static final String LOCOMOTIVE = TOOLTIP_PREFIX + "locomotive";
    }
}
