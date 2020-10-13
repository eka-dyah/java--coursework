package EarthquakeData;

public class MagnitudeFilter implements Filter {
    private final double minMag;
    private final double maxMag;

    public MagnitudeFilter(double min, double max) {
        minMag = min;
        maxMag = max;
    }
    public boolean satisfies(QuakeEntry qe) {
        double mag = qe.getMagnitude();
        return mag >= minMag && mag <= maxMag;
    }
    public String getName() {
        return "Magnitude";
    }
}
