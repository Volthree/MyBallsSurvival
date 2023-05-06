package VelikolepniyTemplate;

import javafx.scene.Scene;

import java.util.Objects;
import java.util.Set;

public class KeyEvent {
    static String keyPressed = "1";
    static String spacePressed = "?";
    static int count;

    public static void keyTyped(Scene s, Set<MyUnit> unitSet) {
        s.setOnKeyPressed(f -> {
            if (!Objects.equals(f.getCode().getChar(), keyPressed)) {
                if (!Objects.equals(f.getCode().getChar(), " ")) {
                    keyPressed = f.getCode().getChar();
                    spacePressed = "?";
                } else {
                    spacePressed = "+";
                    switch (keyPressed){
                        case "C" ->{for (MyUnit unit : unitSet) {
                            if (unit.getStatus() == 1) {
                                if (unit.isHasCrystal()) {
                                    count++;
                                    unit.setStatus(12);
                                    break;
                                } else {
                                    count++;
                                    unit.setStatus(10);
                                    break;
                                }
                            }
                        }}
                        case "X" ->{for (MyUnit unit : unitSet) {
                            if (unit.getStatus() == 1) {
                                if (unit.isHasGem()) {
                                    count++;
                                    unit.setStatus(19);
                                    break;
                                } else {
                                    count++;
                                    unit.setStatus(17);
                                    break;
                                }
                            }
                        }}
                        case "W" ->{for (MyUnit unit : unitSet) {
                            if (unit.getStatus() == 1) {
                                if (unit.isHasTree()) {
                                    count++;
                                    unit.setStatus(4);
                                    break;
                                } else {
                                    count++;
                                    unit.setStatus(3);
                                    break;
                                }
                            }
                        }}
                        case "S" ->{for (MyUnit unit : unitSet) {
                            if (unit.getStatus() != 1) {
                                count++;
                                unit.setStatus(1);
                                break;
                            }
                        }}
                        case "D" ->{for (MyUnit unit : unitSet) {
                            if (unit.getStatus() == 1) {
                                if (ActionInThread.pointSetToDigOrder.size() > 0) {
                                    unit.setStatus(6);
                                }
                                break;
                            }
                        }}
                        case "G" ->{for (MyUnit unit : unitSet) {
                            if (unit.getStatus() == 1) {
                                if (ActionInThread.pointSetToGrowEarth.size() > 0) {
                                    unit.setStatus(13);
                                }
                                break;
                            }
                        }}
                        case "R" ->{for (TestObjectEverything t : ActionInThread.pointSetToGrowEarth) {
                            t.setHeight(t.getHeight());
                            t.setOrderToGrowEarth(false);
                        }
                            ActionInThread.pointSetToGrowEarth.clear();
                            for (TestObjectEverything t : ActionInThread.pointSetToDigOrder) {
                                t.setHeight(t.getHeight());
                                t.setOrderToDig(false);
                            }
                            ActionInThread.pointSetToDigOrder.clear();}
                    }
                }
            }
            TextClass.getKeyText().setText(keyPressed + " " + spacePressed);
        });
    }

    public static char getKeyPressed() {
        return keyPressed.charAt(0);
    }

}
