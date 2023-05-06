package VelikolepniyTemplate;

import static VelikolepniyTemplate.TemplateGenerator.*;

public class FillWater {
    private static int x;
    private static int y;

    public FillWater() {
    }

    public static void fillWater() {
        for (int wh = 0; wh <= fieldSize - cellSize; wh = wh + cellSize) {
            for (int hh = 0; hh <= fieldSize - cellSize; hh = hh + cellSize) {
                x = wh / cellSize;
                y = hh / cellSize;
                TestObjectEverything t = cellObjects[x][y];
                if (t.isDigged()) {
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (x + i > 0 && x + i < size
                                    && y + j > 0 && y + j < size
                                    && !TemplateGenerator.cellObjects[x + i][y + j].isEarth()
                                    && t.getHeight() <= 5
                                    && !TemplateGenerator.cellObjects[x + i][y + j].isStopFillWater()) {
                                t.setDigged(false);
                                t.setEarth(false);
                                t.setStopFillWater(true);
                                t.setCanPath(false);
                                t.setContainCrystal(false);
                                t.setContainWater(true);
                                t.setHeight(TemplateGenerator.cellObjects[x + i][y + j].getHeight());
                            }
                        }
                    }
                }
            }
        }
    }

    public static void fillWaterStep() {
        for (int wh = 0; wh <= fieldSize - cellSize; wh = wh + cellSize) {
            for (int hh = 0; hh <= fieldSize - cellSize; hh = hh + cellSize) {
                x = wh / cellSize;
                y = hh / cellSize;
                cellObjects[x][y].setStopFillWater(false);
            }
        }
    }
}
