package EarthquakeData;

public class Main {
    public static void main(String[] args) {
        EarthQuakeClient eqc = new EarthQuakeClient();
//        eqc.quakesByPhrase();
        ClosestQuakes cq = new ClosestQuakes();
//        cq.findClosestQuakes();
        LargestQuakes lq = new LargestQuakes();
//        lq.findLargestQuakes();
        EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
//        eqc2.testMatchAllFilter2();
        QuakeSortInPlace qsip = new QuakeSortInPlace();
//        qsip.testSort();
        DifferentSorters demo = new DifferentSorters();
        demo.sortByLastWordInTitleThenByMagnitude();
        DistanceSorter ds = new DistanceSorter();
//        ds.sortByDistance();
    }
}
