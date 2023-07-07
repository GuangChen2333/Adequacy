package cn.guangchen233.adequacy.font;

import cn.guangchen233.adequacy.utils.ColorUtil;
import cn.guangchen233.adequacy.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL20;

import java.awt.*;

public class CustomFontRenderer extends FontRenderer {
    private final AWTFontRenderer defaultFont;
    private final AWTFontRenderer boldFont;
    private final AWTFontRenderer italicFont;
    private final AWTFontRenderer boldItalicFont;

    public CustomFontRenderer(Font font) {
        super(
                Minecraft.getMinecraft().gameSettings,
                new ResourceLocation("textures/font/ascii.png"),
                Minecraft.getMinecraft().getTextureManager(),
                false
        );
        this.defaultFont = new AWTFontRenderer(font);
        this.italicFont = new AWTFontRenderer(font.deriveFont(Font.ITALIC));
        this.boldFont = new AWTFontRenderer(font.deriveFont(Font.BOLD));
        this.boldItalicFont = new AWTFontRenderer(font.deriveFont(Font.ITALIC | Font.BOLD));
    }

    public int getHeight() {
        return this.defaultFont.getHeight() / 2;
    }

    public int getSize() {
        return this.defaultFont.getFont().getSize();
    }

    public void drawString(String text, float x, float y, int color) {
        this.drawString(text, x, y, color, false);
    }

    public void drawCenteredString(String text, float x, float y, int color, boolean shadow) {
        this.drawString(text, x - this.getStringWidth(text) / 2.0f, y, color, shadow);
    }

    public void drawCenteredString(String text, float x, float y, int color) {
        this.drawString(text, x - this.getStringWidth(text) / 2.0f, y, color);
    }

    private int drawText(String text, float x, float y, int color, boolean ignoreColor) {
        if (text == null) {
            return 0;
        }
        if (text.isEmpty()) {
            return (int) x;
        }
        GlStateManager.translate(x - 1.5, y + 0.5, 0.0);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableTexture2D();
        int currentColor = color;
        if ((currentColor & 0xFC000000) == 0x0) {
            currentColor |= 0xFF000000;
        }
        int alpha = currentColor >> 24 & 0xFF;
        if (text.contains("§")) {
            String[] parts = text.split("§");
            AWTFontRenderer currentFont = this.defaultFont;
            double width = 0.0;
            boolean randomCase = false;
            boolean bold = false;
            boolean italic = false;
            boolean strikeThrough = false;
            boolean underline = false;
            for (int index = 0; index < parts.length; ++index) {
                String part = parts[index];
                if (!part.isEmpty()) {
                    if (index == 0) {
                        currentFont.drawString(part, width, 0.0, currentColor);
                        width += currentFont.getStringWidth(part);
                    } else {
                        String words = part.substring(1);
                        char type = part.charAt(0);
                        int colorIndex = "0123456789abcdefklmnor".indexOf(type);
                        switch (colorIndex) {
                            case 15: {
                                if (!ignoreColor) {
                                    currentColor = (ColorUtil.hexColors[colorIndex] | alpha << 24);
                                }
                                bold = false;
                                italic = false;
                                randomCase = false;
                                underline = false;
                                strikeThrough = false;
                                break;
                            }
                            case 16: {
                                randomCase = true;
                                break;
                            }
                            case 17: {
                                bold = true;
                                break;
                            }
                            case 18: {
                                strikeThrough = true;
                                break;
                            }
                            case 19: {
                                underline = true;
                                break;
                            }
                            case 20: {
                                italic = true;
                                break;
                            }
                            case 21: {
                                currentColor = color;
                                if ((currentColor & 0xFC000000) == 0x0) {
                                    currentColor |= 0xFF000000;
                                }
                                bold = false;
                                italic = false;
                                randomCase = false;
                                underline = false;
                                strikeThrough = false;
                                break;
                            }
                        }
                        if (bold && italic) {
                            currentFont = this.boldItalicFont;
                        } else if (bold) {
                            currentFont = this.boldFont;
                        } else if (italic) {
                            currentFont = this.italicFont;
                        } else {
                            currentFont = this.defaultFont;
                        }
                        if (randomCase) {
                            currentFont.drawString(ColorUtil.randomMagicText(words), width, 0.0, currentColor);
                        } else {
                            currentFont.drawString(words, width, 0.0, currentColor);
                        }
                        if (strikeThrough) {
                            RenderUtil.drawLine(width / 2.0 + 1.0, currentFont.getHeight() / 3.0, (width + currentFont.getStringWidth(words)) / 2.0 + 1.0, currentFont.getHeight() / 3.0, this.FONT_HEIGHT / 16.0f);
                        }
                        if (underline) {
                            RenderUtil.drawLine(width / 2.0 + 1.0, currentFont.getHeight() / 2.0, (width + currentFont.getStringWidth(words)) / 2.0 + 1.0, currentFont.getHeight() / 2.0, this.FONT_HEIGHT / 16.0f);
                        }
                        width += currentFont.getStringWidth(words);
                    }
                }
            }
        } else {
            this.defaultFont.drawString(text, 0.0, 0.0, currentColor);
        }
        GlStateManager.disableBlend();
        GlStateManager.translate(-(x - 1.5), -(y + 0.5), 0.0);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        return (int) (x + this.getStringWidth(text));
    }

    public int drawString(String text, float x, float y, int color, boolean dropShadow) {
        float currY = y - 3.0f;
        if (text.contains("\n")) {
            String[] parts = text.split("\n");
            float newY = 0.0f;
            for (String s : parts) {
                this.drawText(s, x, currY + newY, color, dropShadow);
                newY += this.getHeight();
            }
            return 0;
        }
        if (dropShadow) {
            GL20.glUseProgram(0);
            int alpha = 1 - (color >> 24 & 0xFF) / 255;
            this.drawText(text, x + 0.5f, currY + 0.5f, new Color(0, 0, 0, alpha * 150).getRGB(), false);
        }
        return this.drawText(text, x, currY, color, false);
    }
}
