package VelikolepniyTemplate;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class RenderImage {
    static BufferedImage bi;
    static WritableImage wr;
    static ImageView imageView;

    public static ImageView toRenderImage(File imageFile) {

        try {
            bi = ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                Color c = new Color(bi.getRGB(j, i));
                int r = c.getRed();
                int b = c.getBlue();
                int gg = c.getGreen();
                if ((r > 240 && b > 240 && gg > 240)) {
                    bi.setRGB(j, i, 255);
                }
            }
        }

        if (bi != null) {
            wr = new WritableImage(bi.getWidth(), bi.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < wr.getWidth(); x++) {
                for (int y = 0; y < wr.getHeight(); y++) {
                    pw.setArgb(x, y, bi.getRGB(x, y));
                }
            }
        }
        imageView = new ImageView(wr);
        imageView.setFitHeight(bi.getHeight());
        imageView.setFitWidth(bi.getWidth());
        return new ImageView(imageView.getImage());
    }
}
