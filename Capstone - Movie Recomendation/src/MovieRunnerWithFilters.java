import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("Rater read data: " + sr.getRaterSize());
        System.out.println("Movie read data: " + MovieDatabase.size());

        ArrayList<Rating> avrg = sr.getAverageRatings(35);
        System.out.println("found: " + avrg.size() + " movies");

        Collections.sort(avrg);
        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYear() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("Rater read data: " + sr.getRaterSize());
        System.out.println("Movie read data: " + MovieDatabase.size());

        ArrayList<Rating> avrg = sr.getAverageRatingsByFilter(20, new YearAfterFilter(2000));
        System.out.println("found: " + avrg.size() + " movies");

        Collections.sort(avrg);
        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("Rater read data: " + sr.getRaterSize());
        System.out.println("Movie read data: " + MovieDatabase.size());

        ArrayList<Rating> avrg = sr.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
        System.out.println("found: " + avrg.size() + " movies");

        Collections.sort(avrg);
        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " | Genres: " + MovieDatabase.getGenres(rating.getItem())) ;
        }
    }

    public void printAverageRatingsByMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("Rater read data: " + sr.getRaterSize());
        System.out.println("Movie read data: " + MovieDatabase.size());

        ArrayList<Rating> avrg = sr.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));
        System.out.println("found: " + avrg.size() + " movies");

        Collections.sort(avrg);
        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " | Minutes: " + MovieDatabase.getMinutes(rating.getItem())) ;
        }
    }

    public void printAverageRatingsByDirector() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("Rater read data: " + sr.getRaterSize());
        System.out.println("Movie read data: " + MovieDatabase.size());

        ArrayList<Rating> avrg = sr.getAverageRatingsByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        System.out.println("found: " + avrg.size() + " movies");

        Collections.sort(avrg);
        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " | Director: " + MovieDatabase.getDirector(rating.getItem())) ;
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("Rater read data: " + sr.getRaterSize());
        System.out.println("Movie read data: " + MovieDatabase.size());

        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1990));
        filter.addFilter(new GenreFilter("Drama"));

        ArrayList<Rating> avrg = sr.getAverageRatingsByFilter(8, filter);
        System.out.println("found: " + avrg.size() + " movies");

        Collections.sort(avrg);
        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " | Genres: " + MovieDatabase.getGenres(rating.getItem())) ;
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("Rater read data: " + sr.getRaterSize());
        System.out.println("Movie read data: " + MovieDatabase.size());

        AllFilters filter = new AllFilters();
        filter.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        filter.addFilter(new MinutesFilter(90, 180));

        ArrayList<Rating> avrg = sr.getAverageRatingsByFilter(3, filter);
        System.out.println("found: " + avrg.size() + " movies");

        Collections.sort(avrg);
        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " | Director: " + MovieDatabase.getDirector(rating.getItem())
                    + " | Minutes: " + MovieDatabase.getMinutes(rating.getItem())) ;
        }
    }
}
