package VelikolepniyTemplate;

import java.awt.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static VelikolepniyTemplate.TemplateGenerator.cellObjects;
import static VelikolepniyTemplate.TemplateGenerator.size;

public class FindNextStepAction {
    static boolean endAdd;
    static Set<PointWeight> pointWeights;
    static Set<PointWeight> pointsContainPath;
    static int countMistake;
    static boolean[][] pointChecked;
    static int[][] weightsFromUnit;
    static int[][] weightFromPurpose;
    static boolean[][] findPathMemory;
    static Point purposeInClass;
    static PointWeight lowestPoint;
    static boolean finalPathFounded;
    static boolean[][] containPath;
    static Point nextStepPoint;
    static Point startUnitPos;
    static MyUnit mU;
    static int count;
    static int countMax;
    static boolean pathMistakeBoolean;

    public static Point findNextStepAction(Point currentPos, Point purpose, MyUnit u) {
        nextStepPoint = new Point(u.getPosition().x, u.getPosition().y);
        purposeInClass = purpose;
        countMax = 0;
        pointChecked = new boolean[size][size];
        containPath = new boolean[size][size];
        weightsFromUnit = new int[size][size];
        weightFromPurpose = new int[size][size];
        pointsContainPath = new HashSet<>();
        pointWeights = new HashSet<>();
        findPathMemory = new boolean[size][size];
        startUnitPos = currentPos;
        endAdd = false;
        mU = u;
        pathMistakeBoolean = false;
        aStar(currentPos.x, currentPos.y);
        return nextStepPoint;
    }

    public static void aStar(int x, int y) {
        if (!endAdd) {
            countMistake = 0;
            pointChecked[x][y] = true;
            for (int a = -1; a <= 1; a++) {
                for (int b = -1; b <= 1; b++) {
                    if (x + a >= 0 && x + a < size && y + b >= 0 && y + b < size) {
                        if ((!pointChecked[x + a][y + b]
                                && (cellObjects[x + a][y + b].isCanPath()))
                                || (Objects.equals(purposeInClass, new Point(x + a, y + b)))) {
                            countMistake++;
                            pointChecked[x + a][y + b] = true;
                            containPath[x + a][y + b] = true;
                            weightsFromUnit[x + a][y + b] = FindDistanceUniversal.findDistanceUniversal(new Point(x, y), new Point(x + a, y + b))
                                    + weightsFromUnit[x][y];

                            weightFromPurpose[x + a][y + b] = FindDistanceUniversal.findDistanceUniversal(new Point(x + a, y + b), purposeInClass);

                            pointWeights.add(new PointWeight(weightsFromUnit[x + a][y + b], weightFromPurpose[x + a][y + b], new Point(x + a, y + b)));
                        } else {
                            pointChecked[x + a][y + b] = true;
                        }
                    }
                }
            }
            if (countMistake == 0) {
                countMax++;
            }
            if (countMax > 300) {
                nextStepPoint.x = startUnitPos.x;
                nextStepPoint.y = startUnitPos.y;
                if (mU.getStatus() != 30 && mU.getStatus() != 31) {
                    mU.setStatus(1);
                } else {
                    mU.setStatus(30);
                }
                endAdd = true;
            }

            if (pointWeights.size() > 0) {
                lowestPoint = new PointWeight(9999, 9999, new Point());
                for (PointWeight pw : pointWeights) {
                    if (pw.getSumWeight() < lowestPoint.getSumWeight()) {
                        lowestPoint = pw;
                    } else if (pw.getSumWeight() == lowestPoint.getSumWeight()) {
                        if (pw.getWeightFromUnit() < lowestPoint.getWeightFromUnit()) {
                            lowestPoint = pw;
                        }
                    }
                }
            } else {
                lowestPoint.setWeightFromPurpose(20);
                endAdd = true;
            }
            if (pointWeights.size() == 0) {
                nextStepPoint.x = startUnitPos.x;
                nextStepPoint.y = startUnitPos.y;
                if (mU.getStatus() != 30 && mU.getStatus() != 31) {
                    mU.setStatus(1);
                } else {
                    mU.setStatus(30);
                }
                endAdd = true;
                System.out.println("pointWeight size == 0");
            }
            pointsContainPath.add(lowestPoint);
            pointWeights.remove(lowestPoint);
            if ((lowestPoint.getWeightFromPurpose() == 0) && countMistake != 0) {
                finalPathFounded = false;
                findNextStepForFinalPath(purposeInClass.x, purposeInClass.y);
                endAdd = true;
            } else {
                aStar(lowestPoint.getPoint().x, lowestPoint.getPoint().y);
            }
        }

    }

    public static void findNextStepForFinalPath(int x, int y) {
        Point p = new Point();
        count = 0;

        while (!finalPathFounded) {
            p.x = x;
            p.y = y;
            int bufferFinalPathUnit = Integer.MAX_VALUE;
            int countMISTAKE = 0;
            for (int a = -1; a <= 1; a = a + 1) {
                for (int b = -1; b <= 1; b = b + 1) {
                    if (x + a >= 0 && x + a < size && y + b >= 0 && y + b < size) {

                        if (!findPathMemory[x + a][y + b]) {
                            if (containPath[x + a][y + b]) {
                                if (weightsFromUnit[x + a][y + b] <= 14) {
                                    switch (mU.getStatus()) {
                                        case 2 -> {
                                            if (weightFromPurpose[x + a][y + b] == 0) {
                                                count = 1;
                                                finalPathFounded = true;
                                                p.x = x + a;
                                                p.y = y + b;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setStatus(1);
                                            }
                                        }
                                        case 31 -> {
                                            if (weightFromPurpose[x + a][y + b] == 0) {
                                                count = 1;
                                                finalPathFounded = true;
                                                p.x = x + a;
                                                p.y = y + b;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setStatus(30);
                                            }
                                        }
                                        case 6 -> {
                                            if (weightFromPurpose[x + a][y + b] == 0) {
                                                count = 1;
                                                finalPathFounded = true;
                                                p.x = x + a;
                                                p.y = y + b;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setStatus(7);
                                                mU.setSleeperCount(10);
                                                cellObjects[x + a][y + b].setOrderToDig(false);
                                                ActionInThread.pointSetToDigOrder.remove(cellObjects[x + a][y + b]);
                                                ActionDig.toDig(x + a, y + b, 3);
                                            }
                                        }
                                        case 13 -> {
                                            if (weightFromPurpose[x + a][y + b] == 0) {
                                                count = 1;
                                                finalPathFounded = true;
                                                p.x = x + a;
                                                p.y = y + b;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setStatus(14);
                                                mU.setSleeperCount(10);
                                                CircleSelect.circleSelect(nextStepPoint.x, nextStepPoint.y, 1, 14);
                                                ActionInThread.pointSetToGrowEarth.remove(cellObjects[nextStepPoint.x][nextStepPoint.y]);
                                            }
                                        }
                                        case 8 -> {
                                            if (weightFromPurpose[x + a][y + b] == 0) {
                                                count = 1;
                                                finalPathFounded = true;
                                                p.x = x + a;
                                                p.y = y + b;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setStatus(9);
                                                mU.setSleeperCount(10);
                                            }
                                        }
                                        case 3 -> {
                                            if (weightFromPurpose[x + a][y + b] <= 14 && cellObjects[x + a][y + b].isContainTree()) {
                                                count = 1;
                                                finalPathFounded = true;
                                                cellObjects[x + a][y + b].setTreeHealth(cellObjects[x + a][y + b].getTreeHealth() - 10);
                                                if (cellObjects[x + a][y + b].getTreeHealth() <= 0) {
                                                    cellObjects[x + a][y + b].setContainTree(false);
                                                    cellObjects[x + a][y + b].setCanPath(true);
                                                }

                                                p.x = startUnitPos.x;
                                                p.y = startUnitPos.y;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setTreeAmount(10);
                                                mU.setHasTree(true);
                                                mU.setStatus(5);
                                                mU.setSleeperCount(10);
                                            }
                                        }
                                        case 15 -> {
                                            if (weightFromPurpose[x + a][y + b] <= 14) {
                                                count = 1;
                                                finalPathFounded = true;
                                                p.x = startUnitPos.x;
                                                p.y = startUnitPos.y;
                                                nextStepPoint.x = x + a;
                                                nextStepPoint.y = y + b;
                                                mU.setStatus(16);
                                                mU.setSleeperCount(10);
                                            }
                                        }
                                        case 10 -> {
                                            if (weightFromPurpose[x + a][y + b] <= 14 && cellObjects[x + a][y + b].isContainCrystal()
                                                    && cellObjects[x + a][y + b].isCrystalFound()) {
                                                count = 1;
                                                finalPathFounded = true;
                                                cellObjects[x + a][y + b].setCrystalFound(false);
                                                cellObjects[x + a][y + b].setContainCrystal(false);
                                                cellObjects[x + a][y + b].setCanPath(true);
                                                ActionDig.pointContainCrystalCollection.remove(cellObjects[x + a][y + b]);
                                                p.x = startUnitPos.x;
                                                p.y = startUnitPos.y;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setCrystalAmount(1);
                                                mU.setHasCrystal(true);
                                                mU.setStatus(11);
                                                mU.setSleeperCount(10);
                                            }
                                        }
                                        case 17 -> {
                                            if (weightFromPurpose[x + a][y + b] <= 14 && cellObjects[x + a][y + b].isContainGem()
                                                    && cellObjects[x + a][y + b].isGemFound()) {
                                                count = 1;
                                                finalPathFounded = true;
                                                cellObjects[x + a][y + b].setGemFound(false);
                                                cellObjects[x + a][y + b].setContainGem(false);
                                                cellObjects[x + a][y + b].setCanPath(true);
                                                ActionDig.pointContainGemCollection.remove(cellObjects[x + a][y + b]);
                                                p.x = startUnitPos.x;
                                                p.y = startUnitPos.y;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setHasGem(true);
                                                mU.setStatus(18);
                                                mU.setSleeperCount(20);
                                            }
                                        }
                                        case 4 -> {
                                            if (cellObjects[x + a][y + b].isHasCC()) {
                                                count = 1;
                                                finalPathFounded = true;
                                                p.x = startUnitPos.x;
                                                p.y = startUnitPos.y;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setTreeAmount(0);
                                                mU.setHasTree(false);
                                                Stats.setWood(Stats.getWood() + 10);
                                                TextClass.getWoodText().setText("Wood: " + Stats.getWood());
                                                mU.setStatus(3);
                                            }
                                        }
                                        case 12 -> {
                                            if (cellObjects[x + a][y + b].isHasCC()) {
                                                count = 1;
                                                finalPathFounded = true;
                                                p.x = startUnitPos.x;
                                                p.y = startUnitPos.y;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setCrystalAmount(0);
                                                mU.setHasCrystal(false);
                                                Stats.setCrystal(Stats.getCrystal() + 1);
                                                TextClass.getCrystalText().setText("Crystal: " + Stats.getCrystal());
                                                mU.setStatus(10);
                                            }
                                        }
                                        case 19 -> {
                                            if (cellObjects[x + a][y + b].isHasCC()) {
                                                count = 1;
                                                finalPathFounded = true;
                                                p.x = startUnitPos.x;
                                                p.y = startUnitPos.y;
                                                nextStepPoint.x = p.x;
                                                nextStepPoint.y = p.y;
                                                mU.setHasGem(false);
                                                mU.setStatus(1);
                                            }
                                        }
                                    }
                                }
                                if (count == 0 && weightsFromUnit[x + a][y + b] < bufferFinalPathUnit) {

                                    countMISTAKE++;
                                    bufferFinalPathUnit = weightsFromUnit[x + a][y + b];
                                    p.x = x + a;
                                    p.y = y + b;
                                }
                            }
                        }
                    }
                }
            }
            if (countMISTAKE == 0) {
                finalPathFounded = true;
            }
            if (weightsFromUnit[p.x][p.y] == 10 || weightsFromUnit[p.x][p.y] == 14) {
                finalPathFounded = true;
            } else {
                x = p.x;
                y = p.y;
            }
        }
        if (count == 0) {
            nextStepPoint.x = p.x;
            nextStepPoint.y = p.y;
        }
    }
}

