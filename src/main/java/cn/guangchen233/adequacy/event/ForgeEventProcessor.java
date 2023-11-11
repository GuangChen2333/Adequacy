package cn.guangchen233.adequacy.event;

import cn.guangchen233.adequacy.Adequacy;
import cn.guangchen233.adequacy.event.events.ClientMessageReceivedEvent;
import cn.guangchen233.adequacy.event.events.GuiRenderEvent;
import cn.guangchen233.adequacy.interfaces.MinecraftInstanceInterface;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class ForgeEventProcessor implements MinecraftInstanceInterface {

    @SubscribeEvent(receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.getEventKey())
                && Keyboard.getEventKey() != 0 && minecraft.currentScreen == null) {
            Adequacy.moduleManager.onKey(Keyboard.getEventKey());
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        Adequacy.eventBus.postEvent(new GuiRenderEvent());
    }

    @SubscribeEvent
    public void onClientReceiveMessage(ClientChatReceivedEvent event) {
        Adequacy.eventBus.postEvent(new ClientMessageReceivedEvent(event.getType(), event.getMessage()));
    }
}
