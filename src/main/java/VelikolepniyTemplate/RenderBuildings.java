package VelikolepniyTemplate;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderBuildings {
    private static ImageView imageViewTower;

    public static void renderBuildings() throws FileNotFoundException {
        File imageFile = new File("src/main/resources/tower.png");
        imageViewTower = RenderImage.toRenderImage(imageFile);
    }

    public static ImageView getImageViewTower() {
        return imageViewTower;
    }
}
