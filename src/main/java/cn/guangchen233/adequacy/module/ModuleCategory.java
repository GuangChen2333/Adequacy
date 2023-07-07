package cn.guangchen233.adequacy.module;

import cn.guangchen233.adequacy.utils.IconFontUtil;

public enum ModuleCategory {
    MOVEMENT("Movement", IconFontUtil.SPEED),
    RENDER("Render", IconFontUtil.BRIGHT),
    WORLD("World", IconFontUtil.EARTH),
    GUI("Gui", IconFontUtil.CAST);

    private final String name;
    private final String icon;

    ModuleCategory(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
