package VelikolepniyTemplate;

import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Set;

import static VelikolepniyTemplate.TemplateGenerator.*;

public class MouseEvent {
    public static void mouseDragged(Scene s, Set<MyUnit> unitSet) {
        s.setOnMouseReleased(click -> {

            Point clickPoint = new Point((int) click.getX(), (int) click.getY());
            if (clickPoint.x >= 0 && clickPoint.x < fieldSize && clickPoint.y >= 0 && clickPoint.y < fieldSize) {
                char key = KeyEvent.getKeyPressed();
                int cpx = clickPoint.x / cellSize;
                int cpy = clickPoint.y / cellSize;
                switch (key) {
                    case 'I' -> {
                        for (int x = -2; x <= 2; x++) {
                            for (int y = -2; y <= 2; y++) {
                                if (cellObjects[cpx + x][cpy + y].isEarth()) {
                                    cellObjects[cpx + x][cpy + y].setCanPath(true);
                                    cellObjects[cpx + x][cpy + y].setDigged(true);
                                    cellObjects[cpx + x][cpy + y].setContainTree(false);
                                    cellObjects[cpx + x][cpy + y]
                                            .setHeight(cellObjects[cpx + x][cpy + y].getHeight() - 1);
                                    if (cellObjects[cpx + x][cpy + y].getHeight() < 6 &&
                                            cellObjects[cpx + x][cpy + y].getHeight() > 0) {
                                        cellObjects[cpx + x][cpy + y]
                                                .setDeep(cellObjects[cpx + x][cpy + y].getDeep() + 1);
                                    }

                                }
                            }
                        }
                    }
                    case 'O' -> {
                        if (cellObjects[cpx][cpy].isEarth()) {

                            cellObjects[cpx][cpy].setDigged(true);
                            cellObjects[cpx][cpy].setContainTree(false);

                            cellObjects[cpx][cpy]
                                    .setHeight(cellObjects[cpx][cpy].getHeight() - 1);

                            if (cellObjects[cpx][cpy].getHeight() < 6 &&
                                    cellObjects[cpx][cpy].getHeight() > 0) {

                                cellObjects[cpx][cpy]
                                        .setDeep(cellObjects[cpx][cpy].getDeep() + 1);
                            }
                        }
                    }
                    case 'A' -> {
                        if (cellObjects[cpx][cpy].isCanPath()) {
                            FindNearestUnit.findNearestUnit(unitSet, new Point(cpx, cpy), 'A');
                        }
                    }
                    case 'Q' -> {
                        if (cellObjects[cpx][cpy].isCanPath()) {
                            FindNearestUnit.findFarthestUnit(unitSet, new Point(cpx, cpy), 'Q');
                        }
                    }
                    case 'T' -> {
                        if (cellObjects[cpx][cpy].isEarth()
                                && !cellObjects[cpx][cpy].isContainTree()
                                && !cellObjects[cpx][cpy].isTreeFall()
                                && !cellObjects[cpx][cpy].isContainUnit()) {

                            if (!cellObjects[cpx][cpy].isDigged()) {
                                cellObjects[cpx][cpy].setCanPath(false);
                                cellObjects[cpx][cpy].setContainTree(true);
                                MainTest.p.getChildren().add(cellObjects[cpx][cpy].getTree());
                            } else {
                                cellObjects[cpx][cpy].setCanPath(false);
                                cellObjects[cpx][cpy].setContainTree(true);
                                cellObjects[cpx][cpy].getTree().setImage(RenderTrees.getTree());
                            }
                        }
                    }
                    case 'B' -> {
                        if (cellObjects[cpx][cpy].isCanPath()
                                && !cellObjects[cpx][cpy].isContainBomb()) {
                            FindNearestUnit.findNearestUnit(unitSet, new Point(cpx, cpy), 'B');
                            cellObjects[cpx][cpy].setContainBomb(true);
                        }
                    }
                    case 'E' -> {
                        if (cellObjects[cpx][cpy].isCanPath()
                                && !cellObjects[cpx][cpy].isContainBomb()
                                && !cellObjects[cpx][cpy].isContainBuilding()
                                && !cellObjects[cpx][cpy].isContainUnit()) {
                            FindNearestUnit.findNearestUnit(unitSet, new Point(cpx, cpy), 'E');
                            for (MyUnit u : unitSet) {
                                if (u.getStatus() == 1) {
                                    cellObjects[cpx][cpy].setContainBuilding(true);
                                    break;
                                }
                            }

                        }
                    }
                    case 'K' -> System.out.println("H " + cellObjects[cpx][cpy].getHeight() + " D " +
                            cellObjects[cpx][cpy].getDeep());
                }
            }
        });
        s.setOnMouseDragged(click -> {
            Point clickPoint = new Point((int) click.getX(), (int) click.getY());
            if (clickPoint.x >= 0 && clickPoint.x < fieldSize && clickPoint.y >= 0 && clickPoint.y < fieldSize) {
                char key = KeyEvent.getKeyPressed();
                int cpx = clickPoint.x / cellSize;
                int cpy = clickPoint.y / cellSize;
                switch (key) {
                    case 'F' -> {
                        if (cellObjects[cpx][cpy].isEarth()
                                && !cellObjects[cpx][cpy].isHasCC()
                                && !cellObjects[cpx][cpy].isContainTree()
                                && !cellObjects[cpx][cpy].isOrderToDig()) {
                            cellObjects[cpx][cpy].setOrderToDig(true, 3);
                            cellObjects[cpx][cpy].getRectangle().setFill(Color.CRIMSON);
                            ActionInThread.pointSetToDigOrder.add(cellObjects[cpx][cpy]);
                        }
                    }
                    case 'H' -> {
                        int countWater = 0;
                        int countOrderToGrow = 0;
                        if (cellObjects[cpx][cpy].isCanPath()
                                && !cellObjects[cpx][cpy].isOrderToGrowEarth()) {
                            for (int x = -1; x <= 1; x++) {
                                for (int y = -1; y <= 1; y++) {
                                    if ((cpx + x) > 0 && (cpx + x) < fieldSize / cellSize
                                            && (cpy + y) > 0 && (cpy + y) < fieldSize / cellSize) {
                                        if (cellObjects[cpx + x][cpy + y].isContainWater()) {
                                            countWater++;
                                        }
                                    }
                                }
                            }
                            if (countWater > 0) {
                                cellObjects[cpx][cpy].setOrderToGrowEarth(true);
                                cellObjects[cpx][cpy].getRectangle().setFill(Color.BLACK);
                                ActionInThread.pointSetToGrowEarth.add(cellObjects[cpx][cpy]);
                            }
                        }
                        if (cellObjects[cpx][cpy].isContainWater()) {
                            for (int x = -1; x <= 1; x++) {
                                for (int y = -1; y <= 1; y++) {
                                    if ((cpx + x) > 0 && (cpx + x) < fieldSize / cellSize
                                            && (cpy + y) > 0 && (cpy + y) < fieldSize / cellSize) {
                                        if (cellObjects[cpx + x][cpy + y].isOrderToGrowEarth()) {
                                            countOrderToGrow++;
                                        }
                                    }
                                }
                            }
                            if (countOrderToGrow > 0) {
                                cellObjects[cpx][cpy].setOrderToGrowEarth(true);
                                cellObjects[cpx][cpy].getRectangle().setFill(Color.BLACK);
                                ActionInThread.pointSetToGrowEarth.add(cellObjects[cpx][cpy]);
                            }
                        }
                    }
                }
            }
        });
    }
}
