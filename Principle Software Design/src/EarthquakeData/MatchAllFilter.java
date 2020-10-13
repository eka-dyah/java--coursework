package EarthquakeData;

import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private final ArrayList<Filter> filters;

    public  MatchAllFilter() {
        filters = new ArrayList<>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter filter : filters) {
            if (!filter.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (Filter f : filters) {
            sb.append(" ").append(f.getName());
        }
        return sb.toString();
    }
}
