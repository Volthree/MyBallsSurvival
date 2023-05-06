package VelikolepniyTemplate;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static VelikolepniyTemplate.TemplateGenerator.cellObjects;

public class FindNearestPointCC {
    static Point startLoopPoint;
    static Set<PointWeight> pointCollection;
    static Point pointCurrent;
    static PointWeight minPointWeight;
    static Point unitPosition;

    public static Point findNearestPointCC(Point unitPoint) {
        unitPosition = unitPoint;
        pointCollection = new HashSet<>();

        startLoopPoint = new Point(CreatingMap.posCCx, CreatingMap.posCCy);
        for (int x = 0; x <= 4; x++) {
            for (int y = 0; y <= 3; y++) {
                if (cellObjects[startLoopPoint.x + x][startLoopPoint.y + y].isHasCC()) {
                    pointCurrent = new Point(startLoopPoint.x + x, startLoopPoint.y + y);
                    pointCollection.add(new PointWeight(FindDistanceUniversal.findDistanceUniversal(pointCurrent, unitPosition), pointCurrent));
                }
            }
        }
        minPointWeight = new PointWeight(Integer.MAX_VALUE, new Point());

        for (PointWeight pw : pointCollection) {
            if (pw.getWeightFromUnit() < minPointWeight.getWeightFromUnit()) {
                minPointWeight = pw;
            }
        }
        return minPointWeight.getPoint();
    }
}
