package cn.guangchen233.adequacy.interfaces;

import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public interface MinecraftInstanceInterface {
    @NotNull
    Minecraft minecraft = Minecraft.getMinecraft();
}
