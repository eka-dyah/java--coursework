package EarthquakeData;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        if (title1.compareTo(title2) == 0) {
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
        return title1.compareTo(title2);
    }
}
