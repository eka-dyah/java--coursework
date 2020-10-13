package EarthquakeData;

public class DepthFilter implements Filter {
    private final double minDepth;
    private final double maxDepth;

    public DepthFilter(double min, double max) {
        minDepth = min;
        maxDepth = max;
    }
    public boolean satisfies(QuakeEntry qe) {
        double mag = qe.getDepth();
        return mag >= minDepth && mag <= maxDepth;
    }

    public String getName() {
        return "Depth";
    }
}
