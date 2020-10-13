package EarthquakeData;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] q1Split = q1.getInfo().split(" ");
        String end1 = q1Split[q1Split.length-1];
        String[] q2Split = q2.getInfo().split(" ");
        String end2 = q2Split[q2Split.length-1];

        if (end1.compareTo(end2) == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return end1.compareTo(end2);
    }
}
