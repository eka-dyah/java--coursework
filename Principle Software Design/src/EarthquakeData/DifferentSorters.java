package EarthquakeData;

import java.util.ArrayList;
import java.util.Collections;

public class DifferentSorters {
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeData/data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);

        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }

    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeData/data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        ArrayList<QuakeEntry> list  = parser.read(source);

        Collections.sort(list, new TitleAndDepthComparator());

        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }

    public void sortByLastWordInTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeData/data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        ArrayList<QuakeEntry> list  = parser.read(source);

        Collections.sort(list, new TitleLastAndMagnitudeComparator());

        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
}
