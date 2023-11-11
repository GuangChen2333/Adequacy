package cn.guangchen233.adequacy.features.modules.render;

import cn.guangchen233.adequacy.Adequacy;
import cn.guangchen233.adequacy.event.annotations.EventHandler;
import cn.guangchen233.adequacy.event.events.GuiRenderEvent;
import cn.guangchen233.adequacy.event.interfaces.Listenable;
import cn.guangchen233.adequacy.module.ModuleCategory;
import cn.guangchen233.adequacy.module.annotations.ModuleDetails;
import cn.guangchen233.adequacy.module.bases.BaseModule;

@ModuleDetails(
        name = "FullBright",
        description = "There will always be light that will guide you on your way",
        category = ModuleCategory.RENDER
)
public class FullBright extends BaseModule implements Listenable {
    private float prevGamma = -1f;

    @Override
    public void onEnable() {
        this.prevGamma = minecraft.gameSettings.gammaSetting;
        Adequacy.eventBus.registerListener(this);
    }

    @Override
    public void onDisable() {
        if (this.prevGamma == -1f) return;
        minecraft.gameSettings.gammaSetting = prevGamma;
        this.prevGamma = -1f;
        Adequacy.eventBus.registerListener(this);
    }

    @EventHandler
    public void onUpdate(GuiRenderEvent event) {
        if (this.isEnabled()) {
            if (minecraft.gameSettings.gammaSetting <= 100f) {
                minecraft.gameSettings.gammaSetting++;
            }
        }
    }
}
