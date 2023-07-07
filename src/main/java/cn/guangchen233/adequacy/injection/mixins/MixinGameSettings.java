package cn.guangchen233.adequacy.injection.mixins;

import cn.guangchen233.adequacy.Adequacy;
import cn.guangchen233.adequacy.event.events.UpdateOptionValueEvent;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameSettings.class)
public class MixinGameSettings {
    @Inject(method = "setOptionValue(Lnet/minecraft/client/settings/GameSettings$Options;I)V", at = @At("HEAD"))
    private void onSetOptionValue(GameSettings.Options settingsOption, int value, CallbackInfo ci) {
        Adequacy.eventBus.postEvent(new UpdateOptionValueEvent(settingsOption, value));
    }
}
