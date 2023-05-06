package VelikolepniyTemplate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderHoleUnit {
    private static Image imageHoleUnit;

    public static void renderHoleUnit() throws FileNotFoundException {
        File imageFile = new File("src/main/resources/holeUnit.png");
        ImageView imageViewHoleUnit = RenderImage.toRenderImage(imageFile);
        imageHoleUnit = imageViewHoleUnit.getImage();
    }

    public static Image getImageHoleUnit() {
        return imageHoleUnit;
    }
}
