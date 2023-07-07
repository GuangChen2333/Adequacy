package cn.guangchen233.adequacy.features.modules.gui;

import cn.guangchen233.adequacy.Adequacy;
import cn.guangchen233.adequacy.event.annotations.EventHandler;
import cn.guangchen233.adequacy.event.events.GuiRenderEvent;
import cn.guangchen233.adequacy.event.events.UpdateOptionValueEvent;
import cn.guangchen233.adequacy.event.interfaces.Listenable;
import cn.guangchen233.adequacy.font.CustomFontRenderer;
import cn.guangchen233.adequacy.module.ModuleCategory;
import cn.guangchen233.adequacy.module.annotations.ModuleDetails;
import cn.guangchen233.adequacy.module.bases.BaseModule;
import cn.guangchen233.adequacy.wrapper.GuiScaleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;

import java.awt.*;

@ModuleDetails(
        name = "Logo",
        description = "Show the logo on the screen",
        category = ModuleCategory.GUI,
        defaultEnable = true
)
public class Logo extends BaseModule implements Listenable {
    CustomFontRenderer font = Adequacy.fontManager.getCustomLightFont(48f);
    GuiScaleValue scaleValue;

    public Logo() {
        scaleValue = GuiScaleValue.byOrdinal(minecraft.gameSettings.guiScale);
    }

    @Override
    public void onEnable() {
        Adequacy.eventBus.registerListener(this);
    }

    @Override
    public void onDisable() {
        Adequacy.eventBus.unregisterListener(this);
    }

    @EventHandler
    public void onGuiRenderEvent(GuiRenderEvent event) {
        font.drawStringWithShadow(
                "Adequacy",
                10,
                10,
                new Color(255, 255, 255).getRGB()
        );
    }

    @EventHandler
    public void onUpdateOptionValueEvent(UpdateOptionValueEvent event) {
        if (event.settingsOption == GameSettings.Options.GUI_SCALE) {
            scaleValue = GuiScaleValue.byOrdinal(minecraft.gameSettings.guiScale);
        }
    }
}
