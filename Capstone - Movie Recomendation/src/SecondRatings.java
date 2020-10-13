/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myPlainRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }

    public SecondRatings (String movieFile, String ratingFile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(movieFile);
        myPlainRaters = firstRatings.loadRaters(ratingFile);

    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myPlainRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters) {
        double count = 0;
        double sumRate = 0;
        for (EfficientRater rater : myPlainRaters) {
            if (rater.getItemsRated().contains(id)) {
                count++;
                sumRate += rater.getRating(id);
            }
        }
        if (minimalRaters <= count) {
            return sumRate/count;
        }
        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> moviesRating = new ArrayList<Rating>();

        for (Movie movie : myMovies) {
            double average = getAverageByID(movie.getID(), minimalRaters);
            if (average != 0.0) {
                moviesRating.add(new Rating(movie.getID(), average));
            }
        }

        return moviesRating;
    }

    public String getTitle(String id) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "Not Found";
    }

    public String getID(String title) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().toLowerCase().equals(title.toLowerCase())) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";
    }

}
