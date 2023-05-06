package VelikolepniyTemplate;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderBomb {

    private static ImageView imageViewBomb;

    public static void renderBomb() throws FileNotFoundException {
        File imageFile = new File("src/main/resources/bomb.png");
        imageViewBomb = RenderImage.toRenderImage(imageFile);
    }

    public static ImageView getImageViewBomb() {
        return new ImageView(imageViewBomb.getImage());
    }
}
