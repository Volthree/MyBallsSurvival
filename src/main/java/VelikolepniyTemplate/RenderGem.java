package VelikolepniyTemplate;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderGem {
    static ImageView imageViewGem;

    public static void renderGem() throws FileNotFoundException {
        File imageFile = new File("src/main/resources/gem.png");
        imageViewGem = RenderImage.toRenderImage(imageFile);
    }

    public static ImageView getImageViewGem() throws FileNotFoundException {
        return new ImageView(imageViewGem.getImage());
    }
}
