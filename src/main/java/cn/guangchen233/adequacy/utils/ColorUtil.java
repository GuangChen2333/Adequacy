package cn.guangchen233.adequacy.utils;

import net.minecraft.util.ChatAllowedCharacters;

import java.util.Random;

public class ColorUtil {
    public static int[] hexColors = new int[16];
    private static final Random random = new Random();
    private static final String magicAllowedCharacters = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ!\"#$%%&'()*+,-./0123456789:;<=>?@ + " +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑ + " +
            "ªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■";

    public static String randomMagicText(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ChatAllowedCharacters.isAllowedCharacter(ch)) {
                int index = ColorUtil.random.nextInt(magicAllowedCharacters.length());
                stringBuilder.append(magicAllowedCharacters.charAt(index));
            }
        }
        return stringBuilder.toString();
    }

    static {
        ColorUtil.hexColors[0] = 0;
        ColorUtil.hexColors[1] = 170;
        ColorUtil.hexColors[2] = 43520;
        ColorUtil.hexColors[3] = 43690;
        ColorUtil.hexColors[4] = 11141120;
        ColorUtil.hexColors[5] = 11141290;
        ColorUtil.hexColors[6] = 16755200;
        ColorUtil.hexColors[7] = 11184810;
        ColorUtil.hexColors[8] = 5592405;
        ColorUtil.hexColors[9] = 5592575;
        ColorUtil.hexColors[10] = 5635925;
        ColorUtil.hexColors[11] = 5636095;
        ColorUtil.hexColors[12] = 16733525;
        ColorUtil.hexColors[13] = 16733695;
        ColorUtil.hexColors[14] = 16777045;
        ColorUtil.hexColors[15] = 16777215;
    }
}
