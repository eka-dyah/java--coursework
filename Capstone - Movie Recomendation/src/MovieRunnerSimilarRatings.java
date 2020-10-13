import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings sr = new FourthRatings();
        System.out.println("Rater read data: " + RaterDatabase.size());
        System.out.println("Movie read data: " + MovieDatabase.size());

        ArrayList<Rating> avrg = sr.getAverageRatings(3);
        System.out.println("found: " + avrg.size() + " movies");

        Collections.sort(avrg);
        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        FourthRatings sr = new FourthRatings();
        System.out.println("Rater read data: " + RaterDatabase.size());
        System.out.println("Movie read data: " + MovieDatabase.size());

        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1980));
        filter.addFilter(new GenreFilter("Romance"));

        ArrayList<Rating> avrg = sr.getAverageRatingsByFilter(1, filter);
        System.out.println("found: " + avrg.size() + " movies");

        Collections.sort(avrg);
        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " | Genres: " + MovieDatabase.getGenres(rating.getItem())) ;
        }
    }

    public void printSimilarRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings sr = new FourthRatings();
        System.out.println("Rater read data: " + RaterDatabase.size());
        System.out.println("Movie read data: " + MovieDatabase.size());

        ArrayList<Rating> avrg = sr.getSimilarRatings("71", 20, 5);
        System.out.println("found: " + avrg.size() + " movies");

        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByDirector() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings sr = new FourthRatings();
        System.out.println("Rater read data: " + RaterDatabase.size());
        System.out.println("Movie read data: " + MovieDatabase.size());

        ArrayList<Rating> avrg = sr.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        System.out.println("found: " + avrg.size() + " movies");

        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings sr = new FourthRatings();
        System.out.println("Rater read data: " + RaterDatabase.size());
        System.out.println("Movie read data: " + MovieDatabase.size());

        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter("Drama"));
        filter.addFilter(new MinutesFilter(80, 160));

        ArrayList<Rating> avrg = sr.getSimilarRatingsByFilter("168", 10, 3, filter);
        System.out.println("found: " + avrg.size() + " movies");

        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings sr = new FourthRatings();
        System.out.println("Rater read data: " + RaterDatabase.size());
        System.out.println("Movie read data: " + MovieDatabase.size());

        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1975));
        filter.addFilter(new MinutesFilter(70, 200));

        ArrayList<Rating> avrg = sr.getSimilarRatingsByFilter("314", 10, 5, filter);
        System.out.println("found: " + avrg.size() + " movies");

        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
}
