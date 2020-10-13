public class MinutesFilter implements Filter {
    private final int myMinMinutes;
    private final int myMaxMinutes;

    public MinutesFilter(int minMinutes, int maxMinutes) {
        myMinMinutes = minMinutes;
        myMaxMinutes = maxMinutes;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= myMinMinutes && MovieDatabase.getMinutes(id) <= myMaxMinutes;
    }
}
