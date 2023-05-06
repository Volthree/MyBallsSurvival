package VelikolepniyTemplate;

import java.util.HashSet;

import static VelikolepniyTemplate.TemplateGenerator.cellObjects;

public class ActionDig {
    public static HashSet<TestObjectEverything> CrystalCollectionForVision = new HashSet<>();
    public static HashSet<TestObjectEverything> GemCollectionForVision = new HashSet<>();
    public static HashSet<TestObjectEverything> pointContainCrystalCollection = new HashSet<>();
    public static HashSet<TestObjectEverything> pointContainGemCollection = new HashSet<>();

    public static void toDig(int xc, int yc, int deep) {
        if (!cellObjects[xc][yc].isContainBuilding() && !cellObjects[xc][yc].isHasCC()) {
            cellObjects[xc][yc].setDigged(true);
            cellObjects[xc][yc].setContainTree(false);
            cellObjects[xc][yc].setCanPath(true);
            for (int k = 0; k < deep; k++) {
                changeHeightDeep(xc, yc);
                crystalFind(xc, yc);
                gemFind(xc, yc);
            }
        }
    }
    private static void changeHeightDeep(int xc, int yc){
        cellObjects[xc][yc].setHeight(cellObjects[xc][yc].getHeight() - 1);
        if (cellObjects[xc][yc].getHeight() < 6 && cellObjects[xc][yc].getHeight() > 0) {
            cellObjects[xc][yc].setDeep(cellObjects[xc][yc].getDeep() + 1);
        }
    }
    private static void crystalFind(int xc, int yc){
        if ((cellObjects[xc][yc].getHeight() == cellObjects[xc][yc].getDeepCrystal())
                && !cellObjects[xc][yc].isCrystalFound()
                && cellObjects[xc][yc].isContainCrystal()) {
            cellObjects[xc][yc].setCrystalFound(true);
            CrystalCollectionForVision.add(cellObjects[xc][yc]);
            pointContainCrystalCollection.add(cellObjects[xc][yc]);
        }
    }
    private static void gemFind(int xc, int yc){
        if ((cellObjects[xc][yc].getHeight() == cellObjects[xc][yc].getDeepGem())
                && !cellObjects[xc][yc].isGemFound()
                && cellObjects[xc][yc].isContainGem()) {
            cellObjects[xc][yc].setGemFound(true);
            cellObjects[xc][yc].setCanPath(false);
            GemCollectionForVision.add(cellObjects[xc][yc]);
            pointContainGemCollection.add(cellObjects[xc][yc]);
        }
    }
}
