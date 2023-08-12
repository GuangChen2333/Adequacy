package cn.guangchen233.adequacy.module.interfaces;

import cn.guangchen233.adequacy.interfaces.Globals;
import cn.guangchen233.adequacy.module.ModuleCategory;
import org.apache.logging.log4j.Logger;

public abstract class AbstractModule implements Globals {
    public String name;
    public String description;
    public Logger logger;
    public ModuleCategory category;
    public int keyBind;
    public boolean show;
    private boolean toggle;

    public void onEnable() {

    }

    public void onDisable() {

    }

    public boolean isEnabled() {
        return toggle;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void setToggle(boolean enable) {
        if (enable) {
            this.enable();
        } else {
            this.disable();
        }
    }

    public void enable() {
        this.onEnable();
        toggle = true;
    }

    public void disable() {
        this.onDisable();
        toggle = false;
    }

    public boolean toggle() {
        if (toggle) {
            this.disable();
        } else {
            this.enable();
        }
        return toggle;
    }
}
