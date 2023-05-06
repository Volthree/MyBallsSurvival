package VelikolepniyTemplate;
import static VelikolepniyTemplate.TemplateGenerator.*;

import javafx.scene.text.Text;

public class TextClass {
    private static Text keyText;
    private static Text woodText;
    private static Text unitText;
    private static Text CrystalText;



    public static void setTextInit(){
            keyText = new Text();
            keyText.setText("?");
            keyText.setX(fieldSize + 10);
            keyText.setY(100);

            woodText = new Text();
            woodText.setText("0");
            woodText.setX(fieldSize + 10);
            woodText.setY(50);

            CrystalText = new Text();
            CrystalText.setText("0");
            CrystalText.setX(fieldSize + 10);
            CrystalText.setY(70);

            unitText = new Text();
            unitText.setText("0");
            unitText.setX(fieldSize + 10);
            unitText.setY(20);
        }
    public static Text getCrystalText() {
        return CrystalText;
    }
    public static Text getKeyText() {
        return keyText;
    }

    public static void setKeyText(Text keyText) {
        TextClass.keyText = keyText;
    }
    public static Text getWoodText() {
        return woodText;
    }

    public static void setWoodText(Text woodText) {
        TextClass.woodText = woodText;
    }

    public static Text getUnitText() {
        return unitText;
    }

    public static void setUnitText(Text unitText) {
        TextClass.unitText = unitText;
    }

}
