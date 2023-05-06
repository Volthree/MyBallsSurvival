package VelikolepniyTemplate;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static VelikolepniyTemplate.TemplateGenerator.*;

public class ActionInThread {
    static Set<TestObjectEverything> pointSetToDigOrder = new HashSet<>();
    static Set<TestObjectEverything> pointSetToGrowEarth = new HashSet<>();
    static Set<TestObjectEverything> pointSetContainsBomb = new HashSet<>();
    static Set<TestObjectEverything> pointSetContainsLightHouse = new HashSet<>();
    private static MyUnit toDeleteUnit = null;
    private static MyUnit toDeleteUnitHole = null;
    private static TestObjectEverything toDeleteHole = null;
    private static int actionCreateHoleUnit = 0;
    private static int countWhenCreateUnitHole = 10;
    private static int slower = 0;
    static Set<TestObjectEverything> toDeleteBomb = new HashSet<>();

    public static void mainAction(Set<MyUnit> hs, Set<MyUnit> myUnitsHole) {
        Platform.runLater(() -> {
            for (MyUnit tob : hs) {
                if (!cellObjects[tob.getPosition().x][tob.getPosition().y].isCanPath() || tob.getUnitHP() <= 0) {
                    toDeleteUnit = tob;
                    continue;
                }
                actionDependStatus(tob);
                refreshHealth(tob);
                imageHPBar(tob);
            }
            bombExplosion();
            deleteExploitedBombs();
            visionCrystal();
            visionGem();
            deleteDeadUnit(hs);
            createHoleUnits(myUnitsHole);
            actionHoleUnits(hs, myUnitsHole);
            deleteHoles();
            deleteUnitsHole(myUnitsHole);
        });
    }

    private static void deleteUnitsHole(Set<MyUnit> myUnitsHole) {
        if (toDeleteUnitHole != null) {
            toDeleteUnitHole.getImageUnit().setImage(null);
            toDeleteUnitHole.getImageHP().setImage(null);
            myUnitsHole.remove(toDeleteUnitHole);
            toDeleteUnitHole = null;
        }
    }

    private static void deleteHoles() {
        for (TestObjectEverything tHole : Hole.collectionHole) {
            if (cellObjects[tHole.getP().x/cellSize][tHole.getP().y/cellSize].isContainWater()) {
                toDeleteHole = tHole;
            }
        }
        if (toDeleteHole != null) {
            toDeleteHole.getImageViewHole().setImage(null);
            Platform.runLater(() -> {
                MainTest.p.getChildren().remove(toDeleteHole.getImageViewHole());
                Hole.collectionHole.remove(cellObjects[toDeleteHole.getP().x/cellSize][toDeleteHole.getP().y/cellSize]);
                toDeleteHole = null;
            });
        }
    }

    private static void actionHoleUnits(Set<MyUnit> hs, Set<MyUnit> myUnitsHole) {
        Platform.runLater(() -> {
            slower++;
            if (slower > 1) {
                slower = 0;
                for (MyUnit uH : myUnitsHole) {
                    if (!cellObjects[uH.getPosition().x][uH.getPosition().y].isCanPath()) {
                        toDeleteUnitHole = uH;
                    }

                    if (uH.getStatus() == 30) {
                        boolean randPFind = false;
                        int countRand = 0;
                        int posCCx = 0;
                        int posCCy = 0;

                        boolean findToKill = FindNearestUnitFromUnitHole.findNearestUnit(hs, uH, 'K');
                        if (!findToKill) {
                            while (!randPFind) {
                                countRand++;
                                posCCx = (int) ((Math.random() * 30) - 15) + uH.getPosition().x;
                                posCCy = (int) ((Math.random() * 30) - 15) + uH.getPosition().y;
                                if (posCCx >= 0 && posCCx < size && posCCy >= 0 && posCCy < size) {
                                    if (cellObjects[posCCx][posCCy].isCanPath()) {
                                        randPFind = true;
                                    }
                                }
                                if (countRand > 100) {
                                    randPFind = true;
                                }
                            }
                            uH.setPurpose(new Point(posCCx, posCCy));

                        }
                        uH.setStatus(31);
                    }
                    if (uH.getStatus() == 31) {
                        uH.move();
                    }
                }
            }
        });
    }

    private static void createHoleUnits(Set<MyUnit> myUnitsHole) {
        actionCreateHoleUnit++;
        if (actionCreateHoleUnit > countWhenCreateUnitHole) {
            countWhenCreateUnitHole = countWhenCreateUnitHole + 100;
            actionCreateHoleUnit = 0;
            Platform.runLater(() -> {
                for (TestObjectEverything t : Hole.collectionHole) {
                    int c = 0;
                    for (int i = 0; i <= 2; i++) {
                        for (int j = 0; j <= 1; j++) {
                            for (int x = -1; x <= 1; x++) {
                                for (int y = -1; y <= 1; y++) {
                                    if (cellObjects[t.getP().x/cellSize + i + x][t.getP().y/cellSize + j + y].isCanPath()
                                            && c == 0) {
                                        CreatingUnits.createAnotherUnit(t.getP().x/cellSize + i + x, t.getP().y/cellSize + j + y, MainTest.p, myUnitsHole, 30);
                                        c = 1;
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private static void deleteDeadUnit(Set<MyUnit> hs) {
        if (toDeleteUnit != null) {
            toDeleteUnit.getImageUnit().setImage(null);
            toDeleteUnit.getImageHP().setImage(null);
            Stats.setCountUnits(Stats.getCountUnits() - 1);
            TextClass.getUnitText().setText("Units: " + Stats.getCountUnits());
            hs.remove(toDeleteUnit);
            toDeleteUnit = null;
        }
    }

    private static void visionGem() {
        if (ActionDig.GemCollectionForVision.size() > 0) {
            Platform.runLater(() -> {
                for (TestObjectEverything po : ActionDig.GemCollectionForVision) {
                    ImageView im = po.getImageViewGem();
                    MainTest.p.getChildren().add(im);
                    im.setTranslateX(po.getP().x - 10);
                    im.setTranslateY(po.getP().y - 12);
                }
                ActionDig.GemCollectionForVision.clear();
            });
        }
    }

    private static void visionCrystal() {
        if (ActionDig.CrystalCollectionForVision.size() > 0) {
            Platform.runLater(() -> {
                for (TestObjectEverything po : ActionDig.CrystalCollectionForVision) {
                    ImageView im = po.getImageViewCrystal();
                    MainTest.p.getChildren().add(im);
                    im.setTranslateX(po.getP().x - 3);
                    im.setTranslateY(po.getP().y - 4);
                }
                ActionDig.CrystalCollectionForVision.clear();
            });
        }
    }

    private static void deleteExploitedBombs() {
        if (toDeleteBomb.size() > 0) {
            for (TestObjectEverything td : toDeleteBomb) {
                td.getImageViewBomb().setImage(null);
                Platform.runLater(() -> MainTest.p.getChildren().remove(td.getImageViewBomb()));
                pointSetContainsBomb.remove(td);
            }
        }
        toDeleteBomb.clear();
    }

    private static void actionDependStatus(MyUnit tob) {
        if (tob.getSleeperCount() <= 0) {
            switch (tob.getStatus()) {
                case 2, 8, 15 -> tob.move();
                case 3 -> {
                    tob.setPurpose(FindNearestTree.findTree(tob.getPosition()));
                    tob.move();
                }
                case 4, 12, 19 -> {
                    tob.setPurpose(FindNearestPointCC.findNearestPointCC(tob.getPosition()));
                    tob.move();
                }
                case 5 -> tob.setStatus(4);
                case 6 -> {
                    if (pointSetToDigOrder.size() > 0) {
                        tob.setPurpose(FindNearestPit.findPit(tob.getPosition()));
                        tob.move();
                    } else tob.setStatus(1);
                }
                case 7, 14 -> {
                    if (!(tob.getSleeperCount() > 0)) tob.setStatus(1);
                }
                case 9 -> {
                    if (!(tob.getSleeperCount() > 0)) {
                        cellObjects[tob.getPosition().x][tob.getPosition().y].setBombTimer(40);
                        pointSetContainsBomb.add(cellObjects[tob.getPosition().x][tob.getPosition().y]);
                        tob.setStatus(1);
                        Platform.runLater(() -> {
                            cellObjects[tob.getPosition().x][tob.getPosition().y].setContainBomb(true);
                            ImageView im = cellObjects[tob.getPosition().x][tob.getPosition().y].getImageViewBomb();
                            MainTest.p.getChildren().add(im);
                            im.setTranslateX(tob.getPosition().x * cellSize - 4);
                            im.setTranslateY(tob.getPosition().y * cellSize - 8);
                        });
                    }
                }
                case 10 -> {
                    if (ActionDig.pointContainCrystalCollection.size() > 0) {
                        tob.setPurpose(FindNearestCrystal.findCrystal(tob.getPosition()));
                        tob.move();
                    } else tob.setStatus(1);
                }
                case 11 -> tob.setStatus(12);
                case 13 -> {
                    if (pointSetToGrowEarth.size() > 0) {
                        tob.setPurpose(FindNearestGrowEarth.findGrowEarth(tob.getPosition()));
                        tob.move();
                    } else tob.setStatus(1);
                }
                case 16 -> {
                    if (!(tob.getSleeperCount() > 0)) {
                        pointSetContainsLightHouse.add(cellObjects[tob.getPurpose().x][tob.getPurpose().y]);
                        tob.setStatus(1);
                        Platform.runLater(() -> {
                            cellObjects[tob.getPurpose().x][tob.getPurpose().y].setCanPath(false);
                            ImageView im = cellObjects[tob.getPurpose().x][tob.getPurpose().y].getImageViewTower();
                            MainTest.p.getChildren().add(im);
                            im.setTranslateX(tob.getPurpose().x * cellSize - 4);
                            im.setTranslateY(tob.getPurpose().y * cellSize - 14);
                            im = RenderConeVision.getImageViewCone60();
                            im.setTranslateX(tob.getPurpose().x * cellSize - 50);
                            im.setTranslateY(tob.getPurpose().y * cellSize - 32);
                            Rotate r = new Rotate();
                            int posGX = CreatingMap.posGemX;
                            int posGY = CreatingMap.posGemY;
                            int posPX = tob.getPurpose().x;
                            int posPY = tob.getPurpose().y;
                            double an = 0;
                            if (posGX < posPX && posGY <= posPY) {
                                an = Math.toDegrees(Math.atan((double) Math.abs(posGY - posPY) / (Math.abs(posGX - posPX))));
                            }
                            if (posGX >= posPX && posGY < posPY) {
                                an = Math.toDegrees(Math.atan((Math.abs(posGX - posPX)) / (double) Math.abs(posGY - posPY))) + 90;
                            }
                            if (posGX <= posPX && posGY > posPY) {
                                an = Math.toDegrees(Math.atan((Math.abs(posGX - posPX)) / (double) Math.abs(posGY - posPY))) - 90;
                            }
                            if (posGX > posPX && posGY >= posPY) {
                                an = Math.toDegrees(Math.atan((double) Math.abs(posGY - posPY) / (Math.abs(posGX - posPX)))) - 180;
                            }
                            r.setAngle(an + ((Math.random() * 50) - 25));
                            r.setPivotX(55);
                            r.setPivotY(32);
                            im.getTransforms().addAll(r);
                            MainTest.p.getChildren().add(im);
                        });
                    }
                }
                case 17 -> {
                    if (ActionDig.pointContainGemCollection.size() > 0) {
                        tob.setPurpose(FindNearestGem.findGem(tob.getPosition()));
                        tob.move();
                    } else tob.setStatus(1);
                }
                case 18 -> tob.setStatus(19);
            }
        } else tob.setSleeperCount(tob.getSleeperCount() - 1);
    }

    private static void refreshHealth(MyUnit tob) {
        if (CreatingMap.posCCx - 3 <= tob.getPosition().x
                && CreatingMap.posCCx + 7 >= tob.getPosition().x
                && CreatingMap.posCCy - 3 <= tob.getPosition().y
                && CreatingMap.posCCy + 7 >= tob.getPosition().y) {
            tob.setUnitHP(2000);
        } else tob.setUnitHP(tob.getUnitHP() - 1);
    }

    private static void imageHPBar(MyUnit tob) {
        switch (tob.getUnitHP() / 10) {
            case 10, 9 -> tob.getImageHP().setImage(RenderHPBar.getImageHPB8());

            case 7 -> tob.getImageHP().setImage(RenderHPBar.getImageHPB7());

            case 6 -> tob.getImageHP().setImage(RenderHPBar.getImageHPB6());

            case 5 -> tob.getImageHP().setImage(RenderHPBar.getImageHPB5());

            case 4 -> tob.getImageHP().setImage(RenderHPBar.getImageHPB4());

            case 3 -> tob.getImageHP().setImage(RenderHPBar.getImageHPB3());

            case 2 -> tob.getImageHP().setImage(RenderHPBar.getImageHPB2());

            case 1 -> tob.getImageHP().setImage(RenderHPBar.getImageHPB1());
        }
    }

    private static void bombExplosion() {
        if (pointSetContainsBomb.size() > 0) {
            for (TestObjectEverything tb : pointSetContainsBomb) {
                if (tb.getBombTimer() > 0) tb.setBombTimer(tb.getBombTimer() - 1);
                else {
                    CircleSelect.circleSelect(tb.getP().x / cellSize, tb.getP().y / cellSize, 5, 9);
                    tb.setContainBomb(false);
                    toDeleteBomb.add(tb);
                }
            }
        }
    }
}
