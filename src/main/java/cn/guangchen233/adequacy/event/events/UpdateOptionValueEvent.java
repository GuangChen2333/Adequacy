package cn.guangchen233.adequacy.event.events;

import cn.guangchen233.adequacy.event.bases.BaseEvent;
import net.minecraft.client.settings.GameSettings;

public class UpdateOptionValueEvent extends BaseEvent {
    public final GameSettings.Options settingsOption;
    public final int value;

    public UpdateOptionValueEvent(GameSettings.Options settingsOption, int value) {
        this.settingsOption = settingsOption;
        this.value = value;
    }
}
