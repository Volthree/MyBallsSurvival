package VelikolepniyTemplate;

import javafx.scene.image.ImageView;

import java.awt.*;

import static VelikolepniyTemplate.TemplateGenerator.cellObjects;
import static VelikolepniyTemplate.TemplateGenerator.cellSize;

public class MyUnit {
    private Point position;
    private int status;
    private Point purpose;
    private ImageView imageUnit;
    private ImageView imageHP;
    private Point nextStep;
    private int treeAmount;
    private boolean hasTree;
    private int sleeperCount;
    private int unitHP;
    private boolean hasCrystal;
    private int crystalAmount;
    private boolean hasGem;


    public MyUnit(Point position, int status) {
        this.position = position;
        this.status = status;
        this.unitHP = 500;
        this.imageHP = RenderHPBar.getImageViewHPB8();
        this.imageUnit = RenderBalls.getImageViewBallWhite();
        this.setStatus(status);
        this.getImageHP().setTranslateX((this.position.x * cellSize));
        this.getImageHP().setTranslateY((this.position.y * cellSize) - 5);
        this.imageUnit.setTranslateX((this.position.x * cellSize) - 12);
        this.imageUnit.setTranslateY((this.position.y * cellSize) - 20);
        cellObjects[position.x][position.y].setContainUnit(true);

    }

    public void move() {
        cellObjects[position.x][position.y].setContainUnit(false);
        nextStep = FindNextStepAction.findNextStepAction(position, purpose, this);
        this.position.x = nextStep.x;
        this.position.y = nextStep.y;
        this.imageHP.setTranslateX((position.x * cellSize));
        this.imageHP.setTranslateY((position.y * cellSize) - 5);
        this.imageUnit.setTranslateX((position.x * cellSize) - 12);
        this.imageUnit.setTranslateY((position.y * cellSize) - 20);
        cellObjects[position.x][position.y].setContainUnit(true);
    }

    public boolean isHasGem() {
        return hasGem;
    }

    public void setHasGem(boolean hasGem) {
        this.hasGem = hasGem;
    }

    public boolean isHasCrystal() {
        return hasCrystal;
    }

    public void setHasCrystal(boolean hasCrystal) {
        this.hasCrystal = hasCrystal;
    }

    public void setCrystalAmount(int crystalAmount) {
        this.crystalAmount = crystalAmount;
    }

    public void setUnitHP(int unitHP) {
        this.unitHP = unitHP;
    }

    public ImageView getImageHP() {
        return imageHP;
    }

    public int getUnitHP() {
        return unitHP;
    }

    public int getSleeperCount() {
        return sleeperCount;
    }

    public void setSleeperCount(int sleeperCount) {
        this.sleeperCount = sleeperCount;
    }

    public boolean isHasTree() {
        return hasTree;
    }

    public void setHasTree(boolean hasTree) {
        this.hasTree = hasTree;
    }

    public void setTreeAmount(int treeAmount) {
        this.treeAmount = treeAmount;
    }

    public Point getPosition() {
        return position;
    }

    public int getStatus() {
        return status;
    }

    public Point getPurpose() {
        return purpose;
    }

    public void setPurpose(Point purpose) {
        this.purpose = purpose;
    }

    public void setStatus(int status) {
        if (status == 1) {
            this.imageUnit.setImage(RenderBalls.getImageWhite());
            //Do nothing
        }
        if (status == 2) {
            this.imageUnit.setImage(RenderBalls.getImageRed());
            //Come to point
        }
        if (status == 3) {
            this.imageUnit.setImage(RenderBalls.getImageYellow());
            //Come to tree
        }
        if (status == 4) {
            this.imageUnit.setImage(RenderBalls.getImageGreen());
            //Come to base (has tree)
        }
        if (status == 5) {
            this.imageUnit.setImage(RenderBalls.getImageDarkGreen());
            //Take tree
        }
        if (status == 6) {
            this.imageUnit.setImage(RenderBalls.getImageDarkYellow());
            //Come to dig
        }
        if (status == 7) {
            this.imageUnit.setImage(RenderBalls.getImageBrown());
            //Digging process
        }
        if (status == 8) {
            this.imageUnit.setImage(RenderBalls.getImageGray());
            //Come to place bomb
        }
        if (status == 9) {
            this.imageUnit.setImage(RenderBalls.getImageBlack());
            //Bomb placing process
        }
        if (status == 10) {
            this.imageUnit.setImage(RenderBalls.getImageBlue());
            //Come to crystal
        }
        if (status == 11) {
            this.imageUnit.setImage(RenderBalls.getImageDarkBlue());
            //Take crystal
        }
        if (status == 12) {
            this.imageUnit.setImage(RenderBalls.getImageDarkBlue());
            //Come to base (has crystal)
        }
        if (status == 13) {
            this.imageUnit.setImage(RenderBalls.getImageBrightPink());
            //Come to grow earth
        }
        if (status == 14) {
            this.imageUnit.setImage(RenderBalls.getImagePink());
            //Growing earth
        }
        if (status == 15) {
            this.imageUnit.setImage(RenderBalls.getImagePurple());
            //Come to build
        }
        if (status == 16) {
            this.imageUnit.setImage(RenderBalls.getImagePurple());
            //Building
        }
        if (status == 17) {
            this.imageUnit.setImage(RenderBalls.getImageBigCyan());
            //Come to gem
        }
        if (status == 18) {
            this.imageUnit.setImage(RenderBalls.getImageBigCyan());
            //Take gem
        }
        if (status == 19) {
            this.imageUnit.setImage(RenderBalls.getImageBigGreen());
            //Come to base (has gem)
        }
        if (status == 30) {
            this.imageUnit.setImage(RenderHoleUnit.getImageHoleUnit());
            //UnitHole stay
            this.imageHP.setImage(null);
        }
        if (status == 31) {
            this.imageUnit.setImage(RenderHoleUnit.getImageHoleUnit());
            //UnitHole come
        }
        this.status = status;
    }

    public ImageView getImageUnit() {
        return imageUnit;
    }
}
