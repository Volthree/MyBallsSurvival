package VelikolepniyTemplate;

import javafx.application.Platform;
import javafx.scene.Group;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class CreatingUnits {
    static Set<MyUnit> myUnits;
    static MyUnit mu;

    public static Set<MyUnit> createMyUnits() {
        myUnits = new HashSet<>();
        return myUnits;
    }

    public static void createAnotherUnit(int x, int y, Group p, Set<MyUnit> s, int st) {
        Platform.runLater(() -> {
            mu = new MyUnit(new Point(x, y), st);
            s.add(mu);
            p.getChildren().add(mu.getImageUnit());
            p.getChildren().add(mu.getImageHP());
        });
    }
}
