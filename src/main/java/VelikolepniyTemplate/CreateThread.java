package VelikolepniyTemplate;

import java.util.Set;

public class CreateThread {
    static int timer;

    public static void createThread1(Set<MyUnit> myUnits, Set<MyUnit> myUnitsHole) {
        Thread t = new Thread(() -> {
            while (true) {
                ActionInThread.mainAction(myUnits, myUnitsHole);
                FillWater.fillWater();
                FillWater.fillWaterStep();
                timer++;
                if (timer > 100 && Hole.collectionHole.size() == 0) {
                    Hole.createHole();
                    timer = 0;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
    }
}
