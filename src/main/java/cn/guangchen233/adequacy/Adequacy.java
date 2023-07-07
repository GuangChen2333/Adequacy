package cn.guangchen233.adequacy;

import cn.guangchen233.adequacy.event.EventBus;
import cn.guangchen233.adequacy.font.FontManager;
import cn.guangchen233.adequacy.module.ModuleManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(
        modid = Adequacy.ID,
        name = Adequacy.NAME,
        version = Adequacy.VERSION,
        useMetadata = true
)
public class Adequacy {
    public static final String ID = "adequacy";
    public static final String VERSION = "0.0.1";
    public static final String NAME = "Adequacy";

    public static final Logger logger = LogManager.getLogger("Adequacy");

    public static EventBus eventBus;
    public static FontManager fontManager;
    public static ModuleManager moduleManager;

    @Mod.EventHandler
    public void onPreInitialization(FMLPreInitializationEvent event) {
        Display.setTitle("Minecraft" + " " + NAME + " " + VERSION + " | " +
                "May all the beauty be blessed."
        );
    }

    @Mod.EventHandler
    public void onInitialization(FMLInitializationEvent event) {
        logger.info("Initializing Adequacy");
        logger.debug("Initializing event bus");
        eventBus = new EventBus();
        logger.debug("Initializing fonts");
        fontManager = new FontManager();
        logger.debug("Initializing modules");
        moduleManager = new ModuleManager();
        logger.info("Adequacy initialized");
    }
}

