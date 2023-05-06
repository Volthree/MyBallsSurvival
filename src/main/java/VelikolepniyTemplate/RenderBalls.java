package VelikolepniyTemplate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderBalls {
    private static ImageView imageViewBallRed;
    private static ImageView imageViewBallWhite;
    private static Image imageDarkBlue;
    private static Image imageBlue;
    private static Image imagePurple;
    private static Image imageRed;
    private static Image imageYellow;
    private static Image imageGreen;
    private static Image imageDarkGreen;
    private static Image imageBrown;
    private static Image imageGray;
    private static Image imageBlack;
    private static Image imageDarkYellow;
    private static Image imageWhite;
    private static Image imagePink;
    private static Image imageBrightPink;
    private static Image imageBigGreen;
    private static Image imageBigCyan;

    public static void renderBalls() throws FileNotFoundException {
        File imageFile = new File("src/main/resources/blueBall.png");
        ImageView imageViewBallBlue = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/darkblueBall.png");
        ImageView imageViewBallDarkBlue = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/greenBall.png");
        ImageView imageViewBallGreen = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/purpleBall.png");
        ImageView imageViewBallPurple = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/redBall.png");
        imageViewBallRed = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/yellowBall.png");
        ImageView imageViewBallYellow = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/darkGreenBall.png");
        ImageView imageViewBallDarkGreen = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/brownBall.png");
        ImageView imageViewBallBrown = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/grayBall.png");
        ImageView imageViewBallGray = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/blackBall.png");
        ImageView imageViewBallBlack = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/whiteBall.png");
        imageViewBallWhite = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/pinkBrightBall.png");
        ImageView imageViewBallPink = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/pinkBrightBall.png");
        ImageView imageViewBallBrightPink = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/bigGreenBallBall.png");
        ImageView imageViewBallBigGreen = RenderImage.toRenderImage(imageFile);
        imageFile = new File("src/main/resources/bigCyanBallBall.png");
        ImageView imageViewBallBigCyan = RenderImage.toRenderImage(imageFile);


        imageDarkBlue = imageViewBallDarkBlue.getImage();
        imageBlue = imageViewBallBlue.getImage();
        imageGreen = imageViewBallGreen.getImage();
        imagePurple = imageViewBallPurple.getImage();
        imageRed = imageViewBallRed.getImage();
        imageYellow = imageViewBallYellow.getImage();
        imageDarkGreen = imageViewBallDarkGreen.getImage();
        imageBrown = imageViewBallBrown.getImage();
        imageGray = imageViewBallGray.getImage();
        imageBlack = imageViewBallBlack.getImage();
        imageDarkYellow = imageViewBallYellow.getImage();
        imageWhite = imageViewBallWhite.getImage();
        imagePink = imageViewBallPink.getImage();
        imageBrightPink = imageViewBallBrightPink.getImage();
        imageBigGreen = imageViewBallBigGreen.getImage();
        imageBigCyan = imageViewBallBigCyan.getImage();
    }

    public static Image getImageBigGreen() {
        return imageBigGreen;
    }

    public static Image getImageBigCyan() {
        return imageBigCyan;
    }

    public static Image getImagePink() {
        return imagePink;
    }

    public static Image getImageBrightPink() {
        return imageBrightPink;
    }

    public static ImageView getImageViewBallWhite() {
        return new ImageView(imageViewBallWhite.getImage());
    }

    public static Image getImageWhite() {
        return imageWhite;
    }

    public static Image getImageDarkYellow() {
        return imageDarkYellow;
    }

    public static Image getImageGray() {
        return imageGray;
    }

    public static Image getImageBlack() {
        return imageBlack;
    }

    public static Image getImageBrown() {
        return imageBrown;
    }

    public static Image getImageDarkGreen() {
        return imageDarkGreen;
    }

    public static Image getImageGreen() {
        return imageGreen;
    }

    public static Image getImageBlue() {
        return imageBlue;
    }

    public static Image getImagePurple() {
        return imagePurple;
    }

    public static Image getImageRed() {
        return imageRed;
    }

    public static Image getImageYellow() {
        return imageYellow;
    }

    public static Image getImageDarkBlue() {
        return imageDarkBlue;
    }

    public static ImageView getImageViewBallRed() throws FileNotFoundException {
        return new ImageView(imageViewBallRed.getImage());
    }
}
