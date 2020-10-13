public class GenreFilter implements Filter {
    private final String myGenre;

    public GenreFilter(String genre) {
        myGenre = genre.toLowerCase();
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).toLowerCase().contains(myGenre);
    }
}
