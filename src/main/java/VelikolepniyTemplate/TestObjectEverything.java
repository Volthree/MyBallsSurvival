package VelikolepniyTemplate;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Objects;

public class TestObjectEverything implements Comparable<TestObjectEverything> {
    private final Point p;
    private int height;
    private final ImageView imageViewTree;
    private final ImageView imageViewBomb;
    private final ImageView imageViewHole;
    private final ImageView imageViewCrystal;
    private final ImageView imageViewTower;
    private final ImageView imageViewGem;
    private final Rectangle rectangle;
    private boolean isEarth = false;
    private boolean digged;
    private boolean stopFillWater = false;
    private int deep;
    private boolean containTree = false;
    private boolean canPath = true;
    private boolean containUnit = false;
    private boolean hasCC;
    private boolean containTreeMemory = false;
    private int treeHealth;
    private boolean treeFall = false;
    private boolean orderToDig;
    private boolean containBomb;
    private int bombTimer;
    private boolean containCrystal = false;
    private int deepCrystal;
    private boolean crystalFound = false;
    private boolean containGem = false;
    private int deepGem;
    private boolean gemFound = false;
    private boolean containWater = false;
    private boolean orderToGrowEarth = false;
    private boolean containBuilding = false;
    private boolean containHole = false;


    public TestObjectEverything(Point p, int unitStatus, int height) {
        this.p = p;
        this.height = height;
        if (height >= 6) this.setEarth(true);
        this.deep = Math.max(6 - height, 0);
        rectangle = new Rectangle(8, 8);
        rectangle.setFill(getColor());
        rectangle.setVisible(true);
        rectangle.setTranslateX(p.x);
        rectangle.setTranslateY(p.y);
        try {
            if (!this.digged) {
                imageViewTree = RenderTrees.getImageViewTree();
                imageViewTree.setTranslateX(p.x - 12);
                imageViewTree.setTranslateY(p.y - 20);
            } else {
                imageViewTree = null;
            }
            ImageView imageViewBall = RenderBalls.getImageViewBallRed();
            imageViewBomb = RenderBomb.getImageViewBomb();
            imageViewCrystal = RenderCrystal.getImageViewCrystal();
            imageViewTower = RenderBuildings.getImageViewTower();
            imageViewGem = RenderGem.getImageViewGem();
            imageViewHole = RenderHole.getImageViewHole();
            imageViewHole.setTranslateX(p.x - 4);
            imageViewHole.setTranslateY(p.y - 5);
            imageViewBomb.setTranslateX(p.x - 12);
            imageViewBomb.setTranslateY(p.y - 20);
            imageViewBall.setTranslateX(p.x - 12);
            imageViewBall.setTranslateY(p.y - 20);
            imageViewTower.setTranslateX(p.x - 12);
            imageViewTower.setTranslateY(p.y - 20);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ImageView getImageViewHole() {
        return imageViewHole;
    }

    public boolean isContainHole() {
        return containHole;
    }

    public void setContainHole(boolean containHole) {
        this.containHole = containHole;
    }

    public ImageView getImageViewTower() {
        return new ImageView(imageViewTower.getImage());
    }


    public boolean isContainBuilding() {
        return containBuilding;
    }

    public void setContainBuilding(boolean containBuilding) {
        this.containBuilding = containBuilding;
    }

    public boolean isOrderToGrowEarth() {
        return orderToGrowEarth;
    }

    public void setOrderToGrowEarth(boolean orderToGrowEarth) {
        this.orderToGrowEarth = orderToGrowEarth;
    }

    public boolean isContainWater() {
        return containWater;
    }

    public void setContainWater(boolean containWater) {
        this.containWater = containWater;
    }

    public ImageView getImageViewGem() {
        return imageViewGem;
    }

    public ImageView getImageViewCrystal() {
        return imageViewCrystal;
    }

    public boolean isGemFound() {
        return gemFound;
    }

    public void setGemFound(boolean gemFound) {
        this.gemFound = gemFound;
    }

    public boolean isContainGem() {
        return containGem;
    }

    public void setContainGem(boolean containGem) {
        this.containGem = containGem;
        if (!containGem) {
            this.imageViewGem.setImage(null);
        }
    }

    public int getDeepGem() {
        return deepGem;
    }

    public void setDeepGem(int deepGem) {
        this.deepGem = deepGem;
    }


    public boolean isCrystalFound() {
        return crystalFound;
    }

    public void setCrystalFound(boolean crystalFound) {
        this.crystalFound = crystalFound;
    }

    public boolean isContainCrystal() {
        return containCrystal;
    }

    public void setContainCrystal(boolean containCrystal) {
        this.containCrystal = containCrystal;
        if (!containCrystal) {
            this.imageViewCrystal.setImage(null);
        }
    }

    public int getDeepCrystal() {
        return deepCrystal;
    }

    public void setDeepCrystal(int deepCrystal) {
        this.deepCrystal = deepCrystal;
    }

    public void setOrderToDig(boolean orderToDig, int orderToDigDeep) {
        this.orderToDig = orderToDig;
    }

    public boolean isTreeFall() {
        return treeFall;
    }

    public void setContainTree(boolean containTree) {
        if (containTree) {
            this.setTreeHealth(100);
        }
        this.containTree = containTree;
        if (digged && !this.containTree) {
            imageViewTree.setImage(null);
        } else if (!this.containTree && containTreeMemory) {
            imageViewTree.setImage(RenderTrees.getTreeFalls());
            treeFall = true;
        }
        if (!containTree && !CreatingMap.createdCC) {
            imageViewTree.setImage(null);
        }
        containTreeMemory = containTree;
    }


    public boolean isContainBomb() {
        return containBomb;
    }

    public void setContainBomb(boolean containBomb) {
        this.containBomb = containBomb;
        if (!containBomb) {
            this.imageViewBomb.setImage(null);
        }
    }

    public int getBombTimer() {
        return bombTimer;
    }

    public void setBombTimer(int bombTimer) {
        this.bombTimer = bombTimer;
    }

    public boolean isOrderToDig() {
        return orderToDig;
    }

    public void setOrderToDig(boolean orderToDig) {
        this.orderToDig = orderToDig;
    }

    public int getTreeHealth() {
        return treeHealth;
    }

    public void setTreeHealth(int treeHealth) {
        this.treeHealth = treeHealth;
    }

    public boolean isHasCC() {
        return hasCC;
    }

    public void setHasCC(boolean hasCC) {
        this.hasCC = hasCC;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public boolean isStopFillWater() {
        return stopFillWater;
    }

    public void setStopFillWater(boolean stopFillWater) {
        this.stopFillWater = stopFillWater;
    }

    public boolean isDigged() {
        return digged;
    }

    public void setDigged(boolean digged) {
        this.digged = digged;
        imageViewTree.setImage(null);
    }

    public boolean isEarth() {
        return isEarth;
    }

    public void setEarth(boolean earth) {
        isEarth = earth;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Point getP() {
        return p;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height >= 0) {
            this.height = height;
            rectangle.setFill(getColor());
        }

    }

    public Color getColor() {
        return ReturnColor.returnColor(height, isEarth, deep, digged);
    }

    public ImageView getTree() {
        return imageViewTree;
    }

    public boolean isContainTree() {
        return containTree;
    }

    public boolean isContainUnit() {
        return containUnit;
    }

    public void setContainUnit(boolean containUnit) {
        this.containUnit = containUnit;
    }

    @Override
    public int compareTo(TestObjectEverything o) {
        if (this.p.y > o.p.y) return 1;
        if (this.p.y == o.p.y) return 1;
        else return -1;
    }

    public boolean isCanPath() {
        return canPath;
    }

    public void setCanPath(boolean canPath) {
        this.canPath = canPath;
    }

    public ImageView getImageViewBomb() {
        return imageViewBomb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestObjectEverything that)) return false;
        return getP().equals(that.getP());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getP());
    }
}
