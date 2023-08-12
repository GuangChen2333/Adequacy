package cn.guangchen233.adequacy.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public interface Globals {
    @NotNull
    Minecraft minecraft = Minecraft.getMinecraft();

    @NotNull
    Minecraft mc = minecraft;

    @Nullable
    default EntityPlayerSP getPlayer() {
        return minecraft.player;
    }

    @Nullable
    default WorldClient getWorld() {
        return minecraft.world;
    }

    @Nullable
    default PlayerControllerMP getPlayerController() {
        return minecraft.playerController;
    }
}
