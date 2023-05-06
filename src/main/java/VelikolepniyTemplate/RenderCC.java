package VelikolepniyTemplate;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderCC {
    static ImageView imageViewCC;

    public static void renderCC() throws FileNotFoundException {
        File imageFile = new File("src/main/resources/CC.png");
        imageViewCC = RenderImage.toRenderImage(imageFile);
    }

    public static ImageView getImageViewCC() throws FileNotFoundException {
        return new ImageView(imageViewCC.getImage());
    }
}
