package VelikolepniyTemplate;

import javafx.scene.paint.Color;

public class ReturnColor {
    public static Color returnColor(int height, boolean isEarth, int deep, boolean dig) {

        if (dig && isEarth) {
            return switch (height) {
                case 0, 1, 2 -> Color.SADDLEBROWN.darker();
                case 3, 4, 5 -> Color.SADDLEBROWN;
                case 6, 7, 8, 9, 10 -> Color.SADDLEBROWN.brighter();
                case 11 -> Color.GRAY;
                case 12 -> Color.GRAY.brighter();
                case 13 -> Color.LIGHTGRAY;
                default -> Color.GAINSBORO;
            };
        }
        if (height >= 6 && isEarth) {
            return switch (height) {
                case 6 -> Color.WHEAT;
                case 7 -> Color.SEAGREEN.brighter();
                case 8 -> Color.SEAGREEN;
                case 9 -> Color.SEAGREEN.darker();
                case 10 -> Color.SEAGREEN.darker().darker();
                case 11 -> Color.GRAY;
                case 12 -> Color.GRAY.brighter();
                case 13 -> Color.LIGHTGRAY;
                default -> Color.GAINSBORO;
            };
        }
        else{
            return switch (deep) {
                case 6 -> Color.ROYALBLUE.darker().darker();
                case 5 -> Color.ROYALBLUE.darker();
                case 4 -> Color.ROYALBLUE;
                case 3 -> Color.ROYALBLUE.brighter();
                case 2 -> Color.SKYBLUE;
                case 1 -> Color.SKYBLUE.brighter();
                default -> Color.NAVY;
            };
        }
    }
}
