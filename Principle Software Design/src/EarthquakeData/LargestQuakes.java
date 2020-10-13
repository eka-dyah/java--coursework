package EarthquakeData;

import java.util.ArrayList;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeData/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> lists = parser.read(source);
        System.out.println("Read from " + lists.size() + " data");

        ArrayList<QuakeEntry> filtered = getLargest(lists, 50);
        for (QuakeEntry qe : filtered) {
            System.out.println(qe);
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        double maxMag = 0;
        int indexMag = 0;
        for (QuakeEntry qe : quakeData) {
            double mag = qe.getMagnitude();
            if (mag > maxMag) {
                maxMag = mag;
                indexMag = quakeData.indexOf(qe);
            }
        }
        return indexMag;
    }

    public ArrayList<QuakeEntry> getLargest (ArrayList<QuakeEntry> quakeData, int howMany) {
        if (quakeData.size() < howMany) {
            return quakeData;
        }

        ArrayList<QuakeEntry> answer = new ArrayList<>();
        while (answer.size() < howMany) {
            int maxMagIndex = indexOfLargest(quakeData);
            answer.add(quakeData.get(maxMagIndex));
            quakeData.remove(maxMagIndex);
        }

        return answer;
    }
}
