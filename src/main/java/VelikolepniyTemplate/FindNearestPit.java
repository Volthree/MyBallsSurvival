package VelikolepniyTemplate;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static VelikolepniyTemplate.TemplateGenerator.cellSize;

public class FindNearestPit {
    static Set<PointWeight> pointCollection;
    static PointWeight minPointWeight;
    static Point unitPosition;

    public static Point findPit(Point unitPoint) {

        unitPosition = unitPoint;
        pointCollection = new HashSet<>();

        if (ActionInThread.pointSetToDigOrder.size() > 0) {
            for (TestObjectEverything tob : ActionInThread.pointSetToDigOrder) {
                int px = tob.getP().x / cellSize;
                int py = tob.getP().y / cellSize;
                pointCollection.add(new PointWeight(FindDistanceUniversal
                        .findDistanceUniversal(new Point(px, py), unitPosition)
                        , new Point(px, py)));
            }
        }
        if (pointCollection.size() > 0) {
            minPointWeight = new PointWeight(9999, new Point());
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
