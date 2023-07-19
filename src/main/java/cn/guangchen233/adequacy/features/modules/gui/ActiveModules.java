package cn.guangchen233.adequacy.features.modules.gui;

import cn.guangchen233.adequacy.Adequacy;
import cn.guangchen233.adequacy.event.annotations.EventHandler;
import cn.guangchen233.adequacy.event.events.GuiRenderEvent;
import cn.guangchen233.adequacy.event.interfaces.Listenable;
import cn.guangchen233.adequacy.font.CustomFontRenderer;
import cn.guangchen233.adequacy.module.ModuleCategory;
import cn.guangchen233.adequacy.module.annotations.ModuleDetails;
import cn.guangchen233.adequacy.module.bases.BaseModule;
import cn.guangchen233.adequacy.module.interfaces.AbstractModule;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.util.List;

@ModuleDetails(
        name = "ActiveModules",
        description = "Show the active modules on the screen",
        category = ModuleCategory.GUI,
        defaultEnable = true,
        showOnList = false
)
public class ActiveModules extends BaseModule implements Listenable {
    CustomFontRenderer font = Adequacy.fontManager.customLightFont;

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
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);
        int width = scaledResolution.getScaledWidth();

        List<AbstractModule> enableModules = Adequacy.moduleManager.getShouldShowModules();
        enableModules.sort((o1, o2) -> font.getStringWidth(o2.name) - font.getStringWidth(o1.name));

        int y = 5;
        for (AbstractModule module : enableModules) {
            int x = width - font.getStringWidth(module.name) - 2;

            font.drawStringWithShadow(module.name, x, y, new Color(255, 255, 255).getRGB());

            y += font.FONT_HEIGHT + 2;
        }
    }
}
