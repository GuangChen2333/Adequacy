package cn.guangchen233.adequacy.wrapper;

public enum GuiScaleValue {
    SMALL(0),
    MEDIUM(1),
    LARGE(2),
    AUTO(3);

    private final int ordinal;

    GuiScaleValue(int ordinal) {
        this.ordinal = ordinal;
    }

    public static GuiScaleValue byOrdinal(int ordinal) {
        for (GuiScaleValue value: GuiScaleValue.values()) {
            if (value.getOrdinal() == ordinal) {
                return value;
            }
        }
        return null;
    }

    public int getOrdinal() {
        return ordinal;
    }
}
