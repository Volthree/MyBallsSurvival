package VelikolepniyTemplate;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static VelikolepniyTemplate.TemplateGenerator.cellObjects;
import static VelikolepniyTemplate.TemplateGenerator.size;

public class CircleSelect {
    public static void circleSelect(int xc, int yc, int radius, int s) {
        Set<Point> pointSet = circlePoints(xc, yc, radius);
        switch (s) {
            case 9 -> {
                for (Point p : pointSet) {
                    if (FindDistanceUniversal.findDistanceUniversal(new Point(xc, yc), new Point(p.x, p.y)) > (radius - 1) * 10) {
                        ActionDig.toDig(p.x, p.y, 1);
                    } else if (FindDistanceUniversal.findDistanceUniversal(new Point(xc, yc), new Point(p.x, p.y)) > (radius / 2) * 10) {
                        ActionDig.toDig(p.x, p.y, 2);
                    } else ActionDig.toDig(p.x, p.y, 3);
                }
                pointSet.clear();
            }
            case 14 -> {
                for (Point p : pointSet) {
                    if (cellObjects[p.x][p.y].isContainWater()) {
                        cellObjects[p.x][p.y].setContainWater(false);
                        cellObjects[p.x][p.y].setEarth(true);
                        cellObjects[p.x][p.y].setDigged(true);
                        cellObjects[p.x][p.y].setCanPath(true);
                        cellObjects[p.x][p.y].setDeep(0);
                        cellObjects[p.x][p.y].setHeight(6);
                    }
                }
                for (Point p : pointSet) {
                    int countWater = 0;
                    for (int k = -1; k <= 1; k++) {
                        for (int j = -1; j <= 1; j++) {
                            if (p.x + k >= 0 && p.x + k < size && p.y + j >= 0 && p.y + j < size) {
                                if (cellObjects[p.x + k][p.y + j].isContainWater()) {
                                    countWater = countWater + 1;
                                }
                            }
                        }
                    }
                    if (countWater == 0) {
                        ActionInThread.pointSetToGrowEarth.remove(cellObjects[p.x][p.y]);
                        cellObjects[p.x][p.y].setHeight(cellObjects[p.x][p.y].getHeight());
                    }
                }
                pointSet.clear();
            }
        }
    }

    private static Set<Point> circlePoints(int xc, int yc, int radius) {
        Set<Point> pointSet = Collections.synchronizedSet(new HashSet<>());
        if (radius > 1) {
            for (int dx = 0; dx <= radius * (Math.sqrt(2) / 2); dx++) {
                int dy = (int) Math.round(Math.sqrt(radius * radius - dx * dx));
                int dxFill = dy;
                while (dxFill >= 0) {
                    int i = yc + (dy) - (dxFill);
                    int yc1 = yc - (dy) + (dxFill);
                    int i2 = xc - (dy) + (dxFill);
                    int i1 = xc + (dy) - (dxFill);
                    if (xc + (dx) >= 0 && xc + (dx) < size && i >= 0 && i < size) {
                        pointSet.add(new Point(xc + (dx), i));
                    }
                    if (xc + (dx) >= 0 && xc + (dx) < size && yc1 >= 0 && yc1 < size) {
                        pointSet.add(new Point(xc + (dx), yc1));
                    }
                    if (xc - (dx) >= 0 && xc - (dx) < size && i >= 0 && i < size) {
                        pointSet.add(new Point(xc - (dx), i));
                    }
                    if (xc - (dx) >= 0 && xc - (dx) < size && yc1 >= 0 && yc1 < size) {
                        pointSet.add(new Point(xc - (dx), yc1));
                    }
                    if (i1 >= 0 && i1 < size && yc + (dx) >= 0 && yc + (dx) < size) {
                        pointSet.add(new Point(i1, yc + (dx)));
                    }
                    if (i2 >= 0 && i2 < size && yc + (dx) >= 0 && yc + (dx) < size) {
                        pointSet.add(new Point(i2, yc + (dx)));
                    }
                    if (i1 >= 0 && i1 < size && yc - (dx) >= 0 && yc - (dx) < size) {
                        pointSet.add(new Point(i1, yc - (dx)));
                    }
                    if (i2 >= 0 && i2 < size && yc - (dx) > 0 && yc - (dx) < size) {
                        pointSet.add(new Point(i2, yc - (dx)));
                    }
                    dxFill = dxFill - 1;
                }
            }
        } else {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    pointSet.add(new Point(xc + x, yc + y));
                }
            }
        }
        return pointSet;
    }
}
