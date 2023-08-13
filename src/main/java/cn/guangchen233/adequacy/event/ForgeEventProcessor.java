package cn.guangchen233.adequacy.event;

import cn.guangchen233.adequacy.Adequacy;
import cn.guangchen233.adequacy.interfaces.MinecraftInstanceInterface;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class ForgeEventProcessor implements MinecraftInstanceInterface {
    public static ForgeEventProcessor INSTANCE;

    @SubscribeEvent(receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.getEventKey())
                && Keyboard.getEventKey() != 0 && minecraft.currentScreen == null) {
            Adequacy.moduleManager.onKey(Keyboard.getEventKey());
        }
    }

    static {
        INSTANCE = new ForgeEventProcessor();
    }
}
