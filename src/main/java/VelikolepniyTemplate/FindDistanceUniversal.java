package VelikolepniyTemplate;

import java.awt.*;

public class FindDistanceUniversal {
    static int countXFromTo;
    static int countYFromTo;
    static int result;

    public static int findDistanceUniversal(Point from, Point to) {
        countXFromTo = 0;
        countYFromTo = 0;
        for (int i = 0; i < Math.abs(from.x - to.x); i++) {
            countXFromTo++;
        }
        for (int k = 0; k < Math.abs(from.y - to.y); k++) {
            countYFromTo++;
        }
        result = (Math.abs(countXFromTo - countYFromTo) * 10) + ((Math.min(countXFromTo, countYFromTo)) * 14);
        return result;
    }
}
