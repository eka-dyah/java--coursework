package EarthquakeData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DistanceSorter {
    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeData/data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        ArrayList<QuakeEntry> list  = parser.read(source);

        // Durham, NC
        Location where = new Location(35.9886, -78.9072);

        Collections.sort(list, new DistanceComparator(where));

        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
}
