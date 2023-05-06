package VelikolepniyTemplate;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderHole {
    private static ImageView imageViewHole;

    public static void renderHole() throws FileNotFoundException {
        File imageFile = new File("src/main/resources/hole.png");
        imageViewHole = RenderImage.toRenderImage(imageFile);
    }

    public static ImageView getImageViewHole() {
        return new ImageView(imageViewHole.getImage());
    }
}
