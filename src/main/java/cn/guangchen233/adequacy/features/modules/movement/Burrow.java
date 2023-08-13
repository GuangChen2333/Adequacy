package cn.guangchen233.adequacy.features.modules.movement;

import cn.guangchen233.adequacy.module.ModuleCategory;
import cn.guangchen233.adequacy.module.annotations.ModuleDetails;
import cn.guangchen233.adequacy.module.bases.BaseModule;
import cn.guangchen233.adequacy.utils.Dispatcher;
import net.minecraft.util.text.TextComponentString;
import org.lwjgl.input.Keyboard;

// TODO: complete this
@ModuleDetails(
        name = "Burrow",
        description = "Clip yourself into a block",
        category = ModuleCategory.MOVEMENT,
        defaultKeyBind = Keyboard.KEY_B
)
public class Burrow extends BaseModule {
    public static Burrow INSTANCE;

    public Burrow() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        Dispatcher.runSafe((context) -> {
            context.player.sendMessage(new TextComponentString("Module Burrow has been enabled"));
        });
    }

    @Override
    public void onDisable() {
        Dispatcher.runSafe((context) -> {
            context.player.sendMessage(new TextComponentString("Module Burrow has been disabled"));
        });
    }
}
