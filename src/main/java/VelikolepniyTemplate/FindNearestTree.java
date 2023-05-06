package VelikolepniyTemplate;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static VelikolepniyTemplate.TemplateGenerator.cellObjects;
import static VelikolepniyTemplate.TemplateGenerator.size;

public class FindNearestTree {
    static Set<PointWeight> pointCollection;
    static PointWeight minPointWeight;
    static Point unitPosition;
    static Point pointCurrent;

    public static Point findTree(Point unitPoint) {

        unitPosition = unitPoint;
        pointCollection = new HashSet<>();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (cellObjects[x][y].isContainTree()) {
                    pointCurrent = new Point(x, y);
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
