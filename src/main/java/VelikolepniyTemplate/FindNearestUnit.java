package VelikolepniyTemplate;

import java.awt.*;
import java.util.Set;

public class FindNearestUnit {
    static int minDistance;
    static int maxDistance;
    static int currentDistance;
    static MyUnit foundedUnit;
    static Set<MyUnit> unitSet;
    static Point purpose;
    static int count;
    static char aChar;

    public static void findNearestUnit(Set<MyUnit> unitSetC, Point purposeC, char ch) {
        minDistance = Integer.MAX_VALUE;
        count = 0;
        unitSet = unitSetC;
        purpose = purposeC;
        aChar = ch;
        for (MyUnit unit : unitSet) {
            if (unit.getStatus() == 1) {
                count++;
                currentDistance = FindDistanceUniversal.findDistanceUniversal(unit.getPosition(), purpose);
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    foundedUnit = unit;
                }
            }
        }
        if (count > 0 && minDistance != 0) {
            assert foundedUnit != null;
            switch (aChar) {
                case 'A' -> foundedUnit.setStatus(2);
                case 'B' -> foundedUnit.setStatus(8);
                case 'G' -> foundedUnit.setStatus(13);
                case 'E' -> foundedUnit.setStatus(15);
            }
            foundedUnit.setPurpose(purpose);
        }
    }

    public static void findFarthestUnit(Set<MyUnit> unitSetC, Point purposeC, char ch) {
        maxDistance = Integer.MIN_VALUE;
        count = 0;
        unitSet = unitSetC;
        purpose = purposeC;
        aChar = ch;
        for (MyUnit unit : unitSet) {
            if (unit.getStatus() == 1) {
                count++;
                currentDistance = FindDistanceUniversal.findDistanceUniversal(unit.getPosition(), purpose);
                if (currentDistance > maxDistance) {
                    maxDistance = currentDistance;
                    foundedUnit = unit;
                }
            }
        }
        if (count > 0 && maxDistance != 0) {
            assert foundedUnit != null;
            if (aChar == 'Q') {
                foundedUnit.setStatus(2);
            }
            foundedUnit.setPurpose(purpose);
        }
    }
}
