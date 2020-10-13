package EarthquakeData;

import java.util.ArrayList;

public class QuakeSortInPlace {
    public int getSmallestIndex(ArrayList<QuakeEntry> quakeData, int start) {
        int smallestIndex = start;
        for (int i=start + 1; i<quakeData.size(); i++) {
            if (quakeData.get(i).getMagnitude() < quakeData.get(smallestIndex).getMagnitude()) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> quakeData) {
        for (int i=0; i<quakeData.size(); i++) {
            int smallestIndex = getSmallestIndex(quakeData, i);
            QuakeEntry smallest = quakeData.get(smallestIndex);
            QuakeEntry current = quakeData.get(i);
            quakeData.set(i, smallest);
            quakeData.set(smallestIndex, current);
        }
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int start) {
        int max = start;
        for (int i=start+1; i<quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(max).getDepth()) {
                max = i;
            }
        }
        return max;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> quakeData) {
        for (int i=0; i<quakeData.size(); i++) {
            if (i >= 70) {
                break;
            }
            System.out.println(i);
            int maxIndex = getLargestDepth(quakeData, i);
            QuakeEntry max = quakeData.get(maxIndex);
            QuakeEntry current = quakeData.get(i);
            quakeData.set(i, max);
            quakeData.set(maxIndex, current);
        }
    }
    public void onePassSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i=0; i< quakeData.size()-1-numSorted; i++) {
            if (quakeData.get(i+1).getMagnitude() < quakeData.get(i).getMagnitude()) {
                QuakeEntry curr = quakeData.get(i);
                QuakeEntry currPlusOne = quakeData.get(i+1);
                quakeData.set(i+1, curr);
                quakeData.set(i, currPlusOne);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData) {
        System.out.println("------------------------- ");
        for (QuakeEntry qe : quakeData) {
            System.out.println(qe);
        }
        for (int i=0; i< quakeData.size()-1; i++) {
            onePassSort(quakeData, i);
            System.out.println("------------------------- " + i);
            for (QuakeEntry qe : quakeData) {
                System.out.println(qe);
            }
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData) {
        for (int i=0; i< quakeData.size()-1; i++) {
            if (!(quakeData.get(i).getMagnitude() <= quakeData.get(i+1).getMagnitude())) {
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData) {
        for (int i=0; i< quakeData.size() - 1; i++) {
            if (!checkInSortedOrder(quakeData)) {
                onePassSort(quakeData, i);
                System.out.println(i);
            } else {
                System.out.println("Already sorted");
                break;
            }
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> quakeData) {
        for (int i=0; i<quakeData.size(); i++) {
            if (!checkInSortedOrder(quakeData)) {
                int smallestIndex = getSmallestIndex(quakeData, i);
                QuakeEntry smallest = quakeData.get(smallestIndex);
                QuakeEntry current = quakeData.get(i);
                quakeData.set(i, smallest);
                quakeData.set(smallestIndex, current);
                System.out.println(i);
            } else {
                System.out.println("Already sorted");
                break;
            }
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeData/data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read " + list.size());
        sortByMagnitudeWithBubbleSortWithCheck(list);
        System.out.println("-------------------------");
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
}
