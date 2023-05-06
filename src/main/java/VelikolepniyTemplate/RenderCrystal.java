package VelikolepniyTemplate;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderCrystal {
    static ImageView imageViewCrystal;

    public static void renderCrystal() throws FileNotFoundException {
        File imageFile = new File("src/main/resources/crystal.png");
        imageViewCrystal = RenderImage.toRenderImage(imageFile);
    }

    public static ImageView getImageViewCrystal() throws FileNotFoundException {
        return new ImageView(imageViewCrystal.getImage());
    }

}
