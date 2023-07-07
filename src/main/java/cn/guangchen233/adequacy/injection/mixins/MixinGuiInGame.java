package cn.guangchen233.adequacy.injection.mixins;

import cn.guangchen233.adequacy.Adequacy;
import cn.guangchen233.adequacy.event.events.GuiRenderEvent;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiIngame.class)
public class MixinGuiInGame {
    @Inject(method = "renderHotbar(Lnet/minecraft/client/gui/ScaledResolution;F)V", at = @At("RETURN"))
    private void onRenderHotbar(ScaledResolution sr, float partialTicks, CallbackInfo callbackInfo) {
        Adequacy.eventBus.postEvent(new GuiRenderEvent());
    }
}
