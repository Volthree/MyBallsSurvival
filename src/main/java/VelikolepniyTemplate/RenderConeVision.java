package VelikolepniyTemplate;

import javafx.scene.image.ImageView;

import java.io.File;

public class RenderConeVision {

    static ImageView imageViewCone60;

    public static void renderConeVision() {
        File imageFile = new File("src/main/resources/angle60.png");
        imageViewCone60 = RenderImage.toRenderImage(imageFile);
    }

    public static ImageView getImageViewCone60() {
        return new ImageView(imageViewCone60.getImage());
    }
}
