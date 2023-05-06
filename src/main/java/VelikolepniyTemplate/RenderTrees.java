package VelikolepniyTemplate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderTrees {
    static ImageView imageViewTree;
    static ImageView imageViewTreeFalls;
    static Image treeFalls;
    static Image tree;

    public static Image getTree() {
        return tree;
    }

    public static void renderTrees() throws FileNotFoundException {
        File imageFile = new File("src/main/resources/derevo.png");
        imageViewTree = RenderImage.toRenderImage(imageFile);
        tree = imageViewTree.getImage();
        imageFile = new File("src/main/resources/derevoFalled.png");
        imageViewTreeFalls = RenderImage.toRenderImage(imageFile);
        treeFalls = imageViewTreeFalls.getImage();
    }

    public static Image getTreeFalls() {
        return treeFalls;
    }

    public static ImageView getImageViewTree() throws FileNotFoundException {
        return new ImageView(imageViewTree.getImage());
    }
}
