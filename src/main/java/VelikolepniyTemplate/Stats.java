package VelikolepniyTemplate;

public class Stats {
    private static int wood;
    private static int countUnits;
    private static int crystal;

    public static int getCrystal() {
        return crystal;
    }

    public static void setCrystal(int crystal) {
        Stats.crystal = crystal;
    }

    public static int getWood() {
        return wood;
    }

    public static void setWood(int wood) {
        Stats.wood = wood;
    }

    public static int getCountUnits() {
        return countUnits;
    }

    public static void setCountUnits(int countUnits) {
        Stats.countUnits = countUnits;
    }
}
