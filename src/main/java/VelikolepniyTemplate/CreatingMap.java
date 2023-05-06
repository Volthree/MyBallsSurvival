package VelikolepniyTemplate;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Set;

import static VelikolepniyTemplate.TemplateGenerator.*;

public class CreatingMap {
    static ImageView img;
    static int posCCx;
    static int posCCy;
    static int posGemX;
    static int posGemY;
    static boolean gemPlaced;
    public static boolean createdCC;

    public static void createMap() {
        int xCordForCell;
        int yCordForCell;
        for (int wh = 0; wh <= fieldSize - cellSize; wh = wh + cellSize) {
            for (int hh = 0; hh <= fieldSize - cellSize; hh = hh + cellSize) {
                int height = (int) array[wh][hh];
                xCordForCell = wh / cellSize;
                yCordForCell = hh / cellSize;
                cellObjects[xCordForCell][yCordForCell] = new TestObjectEverything(new Point(wh, hh), 1, height);
                if (cellObjects[xCordForCell][yCordForCell].getHeight() >= 6) {
                    if ((Math.random() * 100) > (100 - ((height - 7) * 5))) {
                        cellObjects[xCordForCell][yCordForCell].setContainCrystal(true);
                        cellObjects[xCordForCell][yCordForCell].setDeepCrystal(new Random().nextInt((height - 1)));
                    }
                } else {
                    cellObjects[xCordForCell][yCordForCell].setHeight(5);
                    cellObjects[xCordForCell][yCordForCell].setContainWater(true);
                }
            }
        }
    }

    public static void createTrees(Group p) {
        int xCordForCell;
        int yCordForCell;
        for (int wh = 0; wh <= fieldSize - cellSize; wh = wh + cellSize) {
            for (int hh = 0; hh <= fieldSize - cellSize; hh = hh + cellSize) {
                xCordForCell = wh / cellSize;
                yCordForCell = hh / cellSize;
                p.getChildren().add(cellObjects[xCordForCell][yCordForCell].getRectangle());
                if (cellObjects[xCordForCell][yCordForCell].getHeight() < 6) {
                    cellObjects[xCordForCell][yCordForCell].setCanPath(false);
                }
                if (cellObjects[xCordForCell][yCordForCell].getHeight() >= 8 && cellObjects[wh / cellSize][hh / cellSize].getHeight() <= 10) {
                    if (cellObjects[xCordForCell][yCordForCell].isContainTree()) {
                        p.getChildren().add(cellObjects[xCordForCell][yCordForCell].getTree());
                        cellObjects[xCordForCell][yCordForCell].setCanPath(false);
                    }
                }
            }
        }
    }

    public static void generateTreesPosition() {
        for (int wh = 0; wh <= fieldSize - cellSize; wh = wh + cellSize) {
            for (int hh = 0; hh <= fieldSize - cellSize; hh = hh + cellSize) {
                int h = (int) array[wh][hh];
                if ((int) (Math.random() * 800) < 10 && h >= 8 && h <= 10) {
                    generateTrees(wh, hh, 100, 100);
                }
            }
        }
    }

    public static void generateTrees(int w, int h, int rX, int rY) {
        int a = (int) (Math.random() * rX / 2);
        int b = (int) (Math.random() * rY / 2);
        for (int i = 0; i < rX; i = i + cellSize) {
            for (int k = 0; k < rY; k = k + cellSize) {
                if (w + i >= 0 && w + i <= fieldSize - cellSize && h + k >= 0 && h + k <= fieldSize - cellSize && array[w + i][h + k] >= 8 && array[w + i][h + k] <= 10) {
                    if (Math.random() * 10 > 8) {
                        int wi = (w + i) / cellSize;
                        int hk = (h + k) / cellSize;
                        cellObjects[wi][hk].setContainTree(true);
                        if (cellObjects[wi][hk].getHeight() == 8) {
                            if (Math.random() * 100 > 90) {
                                generateTrees(w + i, h + k, a, b);
                            }
                        } else if (cellObjects[wi][hk].getHeight() == 9) {
                            if (Math.random() * 100 > 70) {
                                generateTrees(w + i, h + k, a, b);
                            }
                        } else if (cellObjects[wi][hk].getHeight() == 10) {
                            if (Math.random() * 100 > 30) {
                                generateTrees(w + i, h + k, a, b);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void createCC(Group p, Set<MyUnit> myUnitSet) {
        try {
            img = RenderCC.getImageViewCC();

        } catch (FileNotFoundException e) {
            System.out.println(1);
        }
        createdCC = false;
        boolean find = false;
        while (!find) {
            int count = 0;
            posCCx = ((int) (Math.random() * (fieldSize - (cellSize * 4)))) / cellSize;
            posCCy = ((int) (Math.random() * (fieldSize - (cellSize * 10)))) / cellSize;

            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j <= 6; j++) {
                    if (cellObjects[posCCx + i][posCCy + j].isEarth()) {
                        count++;
                    }
                }
            }
            if (count == 35) {

                find = true;
                p.getChildren().add(img);
                img.setTranslateX((posCCx * cellSize) - 16);
                img.setTranslateY((posCCy * cellSize) - 18);
                for (int i = 0; i <= 4; i++) {
                    for (int j = 0; j <= 5; j++) {

                        cellObjects[posCCx + i][posCCy + j].setContainTree(false);
                        if (j < 4) {
                            cellObjects[posCCx + i][posCCy + j].setCanPath(false);
                            cellObjects[posCCx + i][posCCy + j].setHasCC(true);
                        } else {
                            if (j == 4) {
                                cellObjects[posCCx + i][posCCy + j].setCanPath(true);
                                cellObjects[posCCx + i][posCCy + j].setContainTree(false);
                                CreatingUnits.createAnotherUnit(posCCx + i, posCCy + j, p, myUnitSet, 1);
                                Stats.setCountUnits(Stats.getCountUnits() + 1);
                                TextClass.getUnitText().setText("Units: " + Stats.getCountUnits());
                            }
                        }
                    }
                }
                for (int i = -4; i <= 8; i++) {
                    for (int j = -4; j <= 8; j++) {
                        if ((posCCx + i) * cellSize >= 0 && (posCCx + i) * cellSize <= fieldSize - cellSize && (posCCy + j) * cellSize >= 0 && (posCCy + j) * cellSize <= fieldSize - cellSize) {
                            if (cellObjects[posCCx + i][posCCy + j].getHeight() >= 6
                                    && !cellObjects[posCCx + i][posCCy + j].isHasCC()) {
                                cellObjects[posCCx + i][posCCy + j].setContainTree(false);
                                cellObjects[posCCx + i][posCCy + j].setCanPath(true);

                            }
                        }
                    }
                }
                createdCC = true;
            }

        }
    }

    public static void createGem() {
        gemPlaced = false;
        while (!gemPlaced) {
            posGemX = ((int) (Math.random() * (fieldSize - (cellSize * 4)))) / cellSize;
            posGemY = ((int) (Math.random() * (fieldSize - (cellSize * 10)))) / cellSize;
            if (cellObjects[posGemX][posGemY].getHeight() > 6
                    && !cellObjects[posGemX][posGemY].isContainCrystal()) {
                cellObjects[posGemX][posGemY].setGemFound(false);
                cellObjects[posGemX][posGemY].setContainGem(true);
                cellObjects[posGemX][posGemY]
                        .setDeepGem(new Random().nextInt((cellObjects[posGemX][posGemY].getHeight() - 1)));
                gemPlaced = true;
            }
        }
    }
}
