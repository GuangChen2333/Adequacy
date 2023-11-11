package cn.guangchen233.adequacy.features.modules.chat;

import cn.guangchen233.adequacy.Adequacy;
import cn.guangchen233.adequacy.event.annotations.EventHandler;
import cn.guangchen233.adequacy.event.events.ClientMessageReceivedEvent;
import cn.guangchen233.adequacy.event.interfaces.Listenable;
import cn.guangchen233.adequacy.module.ModuleCategory;
import cn.guangchen233.adequacy.module.annotations.ModuleDetails;
import cn.guangchen233.adequacy.module.bases.BaseModule;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.TextComponentString;

@ModuleDetails(
        name = "HighlightMention",
        description = "Your name will be highlighted when it is mentioned",
        category = ModuleCategory.CHAT,
        defaultEnable = true,
        showOnList = false
)
public class HighlightMention extends BaseModule implements Listenable {
    @Override
    public void onEnable() {
        Adequacy.eventBus.registerListener(this);
    }

    @Override
    public void onDisable() {
        Adequacy.eventBus.registerListener(this);
    }

    @EventHandler
    public void onReceiveChat(ClientMessageReceivedEvent event) {
        String playerName = minecraft.player.getName();
        String message = event.getMessage().getUnformattedText();

        if (message.contains(playerName)) {
            if (message.startsWith("<" + playerName + ">")) return;

            String newMessage = message.replace(
                    playerName,
                    ChatFormatting.GOLD + playerName + ChatFormatting.RESET
            );

            event.setMessage(
                    new TextComponentString(
                            newMessage
                    )
            );
        }
    }
}
