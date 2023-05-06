package VelikolepniyTemplate;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static VelikolepniyTemplate.TemplateGenerator.cellSize;

public class FindNearestGrowEarth {
    static Set<PointWeight> pointCollection;
    static PointWeight minPointWeight;
    static Point unitPosition;

    public static Point findGrowEarth(Point unitPoint) {

        unitPosition = unitPoint;
        pointCollection = new HashSet<>();

        if (ActionInThread.pointSetToGrowEarth.size() > 0) {
            for (TestObjectEverything tob : ActionInThread.pointSetToGrowEarth) {
                if (!tob.isContainWater()) {
                    int px = tob.getP().x / cellSize;
                    int py = tob.getP().y / cellSize;
                    pointCollection.add(new PointWeight(FindDistanceUniversal
                            .findDistanceUniversal(new Point(px, py), unitPosition)
                            , new Point(px, py)));
                }
            }
        }
        if (pointCollection.size() > 0) {
            minPointWeight = new PointWeight(Integer.MAX_VALUE, new Point());
            for (PointWeight pw : pointCollection) {
                if (pw.getWeightFromUnit() < minPointWeight.getWeightFromUnit()) {
                    minPointWeight = pw;
                }
            }
            return minPointWeight.getPoint();
        }
        return unitPosition;
    }
}
