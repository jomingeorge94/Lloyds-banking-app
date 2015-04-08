package ncl.ac.uk.cs.teamone.lloydsstudent;

/**
 * Created by kirstywebb on 06/04/15.
 */
public class Deal {
    private String name;
    private int distance;
    private String distanceMeasure;
    private int loves;
    private int loathes;
    private int iconId;

    public Deal(String name, int distance, String distanceMeasure, int loves, int loathes, int iconId) {
        this.name = name;
        this.distance = distance;
        this.distanceMeasure = distanceMeasure;
        this.loves = loves;
        this.loathes = loathes;
        this.iconId = iconId;
    }

    public String getName() { return  name; }

    public int getDistance() {
        return distance;
    }

    public String getDistanceMeasure() {
        return distanceMeasure;
    }

    public int getLoves() {
        return loves;
    }

    public int getLoathes() {
        return loathes;
    }

    public int getIconId() {
        return iconId;
    }

}
