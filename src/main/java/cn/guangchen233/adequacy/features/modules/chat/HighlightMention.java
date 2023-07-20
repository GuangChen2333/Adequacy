package cn.guangchen233.adequacy.features.modules.chat;

import cn.guangchen233.adequacy.module.ModuleCategory;
import cn.guangchen233.adequacy.module.annotations.ModuleDetails;
import cn.guangchen233.adequacy.module.bases.BaseModule;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleDetails(
        name = "HighlightMention",
        description = "our name will be highlighted when it is mentioned",
        category = ModuleCategory.CHAT,
        defaultEnable = true,
        showOnList = false
)
public class HighlightMention extends BaseModule {
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @SubscribeEvent
    public void onReceiveChat(ServerChatEvent event) {
        if (event.getMessage().contains(minecraft.player.getName())) {
            String playerName = minecraft.player.getName();
            String message = event.getMessage().replace(
                    playerName,
                    ChatFormatting.GOLD.toString() + ChatFormatting.UNDERLINE + playerName
            );

            event.setComponent(
                    new TextComponentString(message)
            );
        }
    }
}
