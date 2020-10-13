package EarthquakeData;

import java.util.Comparator;

public class DistanceComparator implements Comparator<QuakeEntry> {
    Location from;

    public DistanceComparator(Location location) {
        from = location;
    }

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double q1Loc = from.distanceTo(q1.getLocation());
        double q2Loc = from.distanceTo(q2.getLocation());
        return Double.compare(q1Loc, q2Loc);
    }
}
