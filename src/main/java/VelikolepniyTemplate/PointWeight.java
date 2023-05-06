package VelikolepniyTemplate;

import java.awt.*;

public class PointWeight implements Comparable<PointWeight> {
    private int weightFromUnit;
    private int weightFromPurpose;
    private boolean checked;
    private Point point;

    public PointWeight(int weightFromUnit, int weightFromPurpose, Point point) {
        this.weightFromUnit = weightFromUnit;
        this.weightFromPurpose = weightFromPurpose;
        this.point = point;
    }

    public PointWeight(int weightFromUnit, Point point) {
        this.weightFromUnit = weightFromUnit;
        this.point = point;
        this.checked = false;
    }

    public int getSumWeight() {
        return weightFromUnit + weightFromPurpose;
    }

    public int getWeightFromUnit() {
        return weightFromUnit;
    }

    public int getWeightFromPurpose() {
        return weightFromPurpose;
    }

    public void setWeightFromPurpose(int weightFromPurpose) {
        this.weightFromPurpose = weightFromPurpose;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public int compareTo(PointWeight o) {
        if (this.getPoint().x > o.getPoint().x) {
            return 1;
        } else return -1;
    }
}
