package cn.guangchen233.adequacy.font;

import cn.guangchen233.adequacy.Adequacy;
import cn.guangchen233.adequacy.interfaces.AbstractMinecraftInstance;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class FontManager extends AbstractMinecraftInstance {
    public FontRenderer minecraftFont = minecraft.fontRenderer;
    public final CustomFontRenderer customFont;
    public final CustomFontRenderer customLightFont;
    public final CustomFontRenderer iconFont;

    public CustomFontRenderer getCustomFont(float size) {
        try {
            return new CustomFontRenderer(
                    Font.createFont(
                            Font.PLAIN,
                            Objects.requireNonNull(
                                    Adequacy.class.getResourceAsStream("/fonts/TextFont.ttf")
                            )
                    ).deriveFont(size)
            );
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomFontRenderer getIconFont(float size) {
        try {
            return new CustomFontRenderer(
                    Font.createFont(
                            Font.PLAIN,
                            Objects.requireNonNull(
                                    Adequacy.class.getResourceAsStream("/fonts/IconFont.ttf")
                            )
                    ).deriveFont(size)
            );
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomFontRenderer getCustomLightFont(float size) {
        try {
            return new CustomFontRenderer(
                    Font.createFont(
                            Font.PLAIN,
                            Objects.requireNonNull(
                                    Adequacy.class.getResourceAsStream("/fonts/TextFontLight.ttf")
                            )
                    ).deriveFont(size)
            );
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FontManager() {
        this.customFont = getCustomFont(35f);
        this.iconFont = getIconFont(35f);
        this.customLightFont = getCustomLightFont(35f);
    }
}
