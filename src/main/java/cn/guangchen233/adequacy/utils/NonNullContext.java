package cn.guangchen233.adequacy.utils;

import com.google.common.base.MoreObjects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

public class NonNullContext {
    @NotNull
    public final Minecraft mc;
    @NotNull
    public final EntityPlayerSP player;
    @NotNull
    public final WorldClient world;
    @NotNull
    public final NetHandlerPlayClient connection;

    public NonNullContext(@NotNull Minecraft mc, @NotNull EntityPlayerSP player,
                          @NotNull WorldClient world, @NotNull NetHandlerPlayClient connection) {
        this.mc = mc;
        this.player = player;
        this.world = world;
        this.connection = connection;
    }

    @Nullable
    public static <T> T runSafe(Function<NonNullContext, T> func) {
        NonNullContext instance = NonNullContext.newInstance();
        return instance == null ? null : func.apply(instance);
    }

    public static void runSafe(Consumer<NonNullContext> func) {
        NonNullContext instance = NonNullContext.newInstance();
        if (instance != null) func.accept(instance);
    }

    @Nullable
    private static NonNullContext newInstance() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        WorldClient world = mc.world;
        NetHandlerPlayClient connection = mc.getConnection();
        if (player == null || world == null || connection == null) return null;
        return new NonNullContext(mc, player, world, connection);
    }
}
