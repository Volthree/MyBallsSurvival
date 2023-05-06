package VelikolepniyTemplate;

import javafx.application.Platform;

import java.util.HashSet;
import java.util.Set;

import static VelikolepniyTemplate.TemplateGenerator.*;

public class Hole {
    public static Set<TestObjectEverything> collectionHole = new HashSet<>();

    public static void createHole() {
        int countTry = 0;
        boolean holePlaced = false;
        while (!holePlaced) {
            countTry++;
            int count = 0;
            int posCCx = ((int) (Math.random() * (fieldSize - (cellSize * 4)))) / cellSize;
            int posCCy = ((int) (Math.random() * (fieldSize - (cellSize * 10)))) / cellSize;

            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 1; j++) {
                    if (cellObjects[posCCx + i][posCCy + j].isEarth()
                            && !cellObjects[posCCx + i][posCCy + j].isContainHole()
                            && !cellObjects[posCCx + i][posCCy + j].isContainUnit()
                            && cellObjects[posCCx + i][posCCy + j].isCanPath()) {
                        count++;
                    }
                }
            }
            if (countTry > 100) {
                holePlaced = true;
            }
            if (count == 6) {
                holePlaced = true;
                Platform.runLater(() -> MainTest.p.getChildren().add(cellObjects[posCCx][posCCy].getImageViewHole()));

                collectionHole.add(cellObjects[posCCx][posCCy]);

                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 1; j++) {
                        cellObjects[posCCx + i][posCCy + j].setContainHole(true);
                        cellObjects[posCCx + i][posCCy + j].setCanPath(false);
                    }
                }
            }
        }
    }
}
