package VelikolepniyTemplate;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Set;

public class MainTest extends Application {
    public static Group p;
    public static Group pVision;

    public static void main(String[] args) {
        TemplateGenerator.generate();
        p = new Group();
        pVision = new Group();
        launch();
    }

    public void start(Stage primaryStage) {

        TextClass.setTextInit();
        p.getChildren().add(TextClass.getKeyText());
        p.getChildren().add(TextClass.getUnitText());
        p.getChildren().add(TextClass.getWoodText());
        p.getChildren().add(TextClass.getCrystalText());
        CreatingMap.createMap();
        CreatingMap.generateTreesPosition();
        CreatingMap.createTrees(p);
        Set<MyUnit> myUnits = Collections.synchronizedSet(CreatingUnits.createMyUnits());
        Set<MyUnit> myUnitsHole = CreatingUnits.createMyUnits();
        CreatingMap.createCC(p, myUnits);
        CreatingMap.createGem();
        CreateThread.createThread1(myUnits, myUnitsHole);

        Scene s = new Scene(p, 800, 800);
        KeyEvent.keyTyped(s, myUnits);
        MouseEvent.mouseDragged(s, myUnits);

        primaryStage.setTitle("MyBallsSurvival");
        primaryStage.setScene(s);
        primaryStage.setMinHeight(900);
        primaryStage.setMinWidth(900);
        primaryStage.setMaxHeight(900);
        primaryStage.setMaxWidth(900);
        primaryStage.show();
    }

}