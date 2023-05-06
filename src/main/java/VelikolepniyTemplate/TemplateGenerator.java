package VelikolepniyTemplate;

import java.io.FileNotFoundException;

public class TemplateGenerator {
    public static final int fieldSize = 712;
    public static final int cellSize = 8;
    static int size = fieldSize / cellSize;
    public static volatile TestObjectEverything[][] cellObjects = new TestObjectEverything[size][size];
    public static final double[][] array = new double[fieldSize + 1][fieldSize + 1];

    public TemplateGenerator() {
    }

    public static void generate() {
        try {
            RenderTrees.renderTrees();
            RenderBalls.renderBalls();
            RenderHPBar.renderHPBs();
            RenderBomb.renderBomb();
            RenderCC.renderCC();
            RenderCrystal.renderCrystal();
            RenderGem.renderGem();
            RenderConeVision.renderConeVision();
            RenderBuildings.renderBuildings();
            RenderHole.renderHole();
            RenderHoleUnit.renderHoleUnit();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        HeightMap heightMap = new HeightMap();
        heightMap.heightMap(fieldSize, array);
        Alignment alignment = new Alignment();
        alignment.alignmentMethod(array, fieldSize);
    }
}
