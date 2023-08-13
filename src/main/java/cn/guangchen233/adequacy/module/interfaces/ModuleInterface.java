package cn.guangchen233.adequacy.module.interfaces;

import cn.guangchen233.adequacy.interfaces.MinecraftInstanceInterface;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import org.jetbrains.annotations.Nullable;

public interface ModuleInterface extends MinecraftInstanceInterface {
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
