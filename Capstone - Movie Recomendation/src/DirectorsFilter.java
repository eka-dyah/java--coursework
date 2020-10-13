public class DirectorsFilter implements Filter {
    private final String[] myDirector;

    public DirectorsFilter(String director) {
        myDirector = director.split("\\b,\\b");
    }

    @Override
    public boolean satisfies(String id) {
        String movieDirector = MovieDatabase.getDirector(id);
        for (String director : myDirector) {
            if (movieDirector.contains(director)) {
                return true;
            }
        }
        return false;
    }
}
