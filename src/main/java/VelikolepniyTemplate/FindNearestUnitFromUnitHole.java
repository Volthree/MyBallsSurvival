package VelikolepniyTemplate;

import java.awt.*;
import java.util.Set;

public class FindNearestUnitFromUnitHole {
    static int minDistance;
    static int currentDistance;
    static MyUnit foundedUnit;
    static int count;

    public static boolean findNearestUnit(Set<MyUnit> unitSet, MyUnit unitHole, char ch) {
        minDistance = Integer.MAX_VALUE;
        count = 0;
        for (MyUnit unit : unitSet) {
            count++;
            currentDistance = FindDistanceUniversal.findDistanceUniversal(unit.getPosition(), unitHole.getPosition());
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                foundedUnit = unit;
            }
        }
        if (count > 0 && minDistance != 0) {
            assert foundedUnit != null;
            if (ch == 'K') {
                if (minDistance < 200) {
                    unitHole.setPurpose(new Point(foundedUnit.getPosition().x, foundedUnit.getPosition().y));
                    return true;
                }
            }
        }
        if (minDistance == 0) {
            CircleSelect.circleSelect(unitHole.getPosition().x, unitHole.getPosition().y, 3, 9);
        }
        return false;
    }
}
