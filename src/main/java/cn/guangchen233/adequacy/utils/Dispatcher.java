package cn.guangchen233.adequacy.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

public class Dispatcher {
    @NotNull
    public final Minecraft mc;
    @NotNull
    public final EntityPlayerSP player;
    @NotNull
    public final WorldClient world;
    @NotNull
    public final NetHandlerPlayClient connection;

    public Dispatcher(@NotNull Minecraft mc, @NotNull EntityPlayerSP player,
                      @NotNull WorldClient world, @NotNull NetHandlerPlayClient connection) {
        this.mc = mc;
        this.player = player;
        this.world = world;
        this.connection = connection;
    }

    @Nullable
    public static <T> T runSafe(Function<Dispatcher, T> func) {
        Dispatcher instance = Dispatcher.newInstance();
        return instance == null ? null : func.apply(instance);
    }

    public static void runSafe(Consumer<Dispatcher> func) {
        Dispatcher instance = Dispatcher.newInstance();
        if (instance != null) func.accept(instance);
    }

    @Nullable
    private static Dispatcher newInstance() {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP player = minecraft.player;
        WorldClient world = minecraft.world;
        NetHandlerPlayClient connection = minecraft.getConnection();
        if (player == null || world == null || connection == null) return null;
        return new Dispatcher(minecraft, player, world, connection);
    }
}
