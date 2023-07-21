package cn.guangchen233.adequacy.features.modules.render;

import cn.guangchen233.adequacy.event.interfaces.Listenable;
import cn.guangchen233.adequacy.module.ModuleCategory;
import cn.guangchen233.adequacy.module.annotations.ModuleDetails;
import cn.guangchen233.adequacy.module.bases.BaseModule;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisable() {
        if (this.prevGamma == -1f) return;
        minecraft.gameSettings.gammaSetting = prevGamma;
        this.prevGamma = -1f;
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (this.isEnabled()) {
            if (minecraft.gameSettings.gammaSetting <= 100f) {
                minecraft.gameSettings.gammaSetting++;
            }
        }
    }
}
