package VelikolepniyTemplate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderHPBar {
    private static ImageView imageViewHPB8;
    private static Image imageHPB1;
    private static Image imageHPB2;
    private static Image imageHPB3;
    private static Image imageHPB4;
    private static Image imageHPB5;
    private static Image imageHPB6;
    private static Image imageHPB7;
    private static Image imageHPB8;

    public static void renderHPBs() throws FileNotFoundException {
        File imageFile = new File("src/main/resources/Hpbar1.png");
        ImageView imageViewHPB1 = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/Hpbar2.png");
        ImageView imageViewHPB2 = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/Hpbar3.png");
        ImageView imageViewHPB3 = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/Hpbar4.png");
        ImageView imageViewHPB4 = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/Hpbar5.png");
        ImageView imageViewHPB5 = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/Hpbar6.png");
        ImageView imageViewHPB6 = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/Hpbar7.png");
        ImageView imageViewHPB7 = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/Hpbar8.png");
        imageViewHPB8 = RenderImage.toRenderImage(imageFile);

        imageHPB1 = imageViewHPB1.getImage();
        imageHPB2 = imageViewHPB2.getImage();
        imageHPB3 = imageViewHPB3.getImage();
        imageHPB4 = imageViewHPB4.getImage();
        imageHPB5 = imageViewHPB5.getImage();
        imageHPB6 = imageViewHPB6.getImage();
        imageHPB7 = imageViewHPB7.getImage();
        imageHPB8 = imageViewHPB8.getImage();
    }

    public static ImageView getImageViewHPB8() {
        return new ImageView(imageViewHPB8.getImage());
    }

    public static Image getImageHPB1() {
        return imageHPB1;
    }

    public static Image getImageHPB2() {
        return imageHPB2;
    }

    public static Image getImageHPB3() {
        return imageHPB3;
    }

    public static Image getImageHPB4() {
        return imageHPB4;
    }

    public static Image getImageHPB5() {
        return imageHPB5;
    }

    public static Image getImageHPB6() {
        return imageHPB6;
    }

    public static Image getImageHPB7() {
        return imageHPB7;
    }

    public static Image getImageHPB8() {
        return imageHPB8;
    }
}
