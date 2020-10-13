package EarthquakeData;

public class DistanceFilter implements Filter {
    private final double maxDist;
    private final Location loc;

    public DistanceFilter(Location location, double max) {
        loc = location;
        maxDist = max;
    }
    public boolean satisfies(QuakeEntry qe) {
        double distance = loc.distanceTo(qe.getLocation()) / 1000;
        return distance <= maxDist;
    }
    public String getName() {
        return "Distance";
    }
}
