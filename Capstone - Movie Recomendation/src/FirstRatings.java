import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> answer = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord rec : fr.getCSVParser()) {
            Movie movie = new Movie(rec.get("id"), rec.get("title"), rec.get("year"), rec.get("genre"),
                    rec.get("director"), rec.get("country"), rec.get("poster"), Integer.parseInt(rec.get("minutes")));
            answer.add(movie);
        }
        return answer;
    }

    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        HashMap<String, Integer> director = new HashMap<String, Integer>();
        int comedy = 0;
        int greater150min = 0;
        for (Movie movie : movies) {
            if (movie.getGenres().toLowerCase().contains("comedy")) {
                comedy++;
                System.out.println(movie);
            }
            if (movie.getMinutes() > 150) {
                greater150min++;
                System.out.println(movie);
            }
            if (director.containsKey(movie.getDirector())) {
                director.put(movie.getDirector(), director.get(movie.getDirector()) + 1);
            } else {
                director.put(movie.getDirector(), 1);
            }
        }
        System.out.println("Size: " + movies.size());
        System.out.println("Comedy: " + comedy);
        System.out.println("Greater than 150 min: " + greater150min);

        int directorMax = 0;
        String directorName = "";
        for (String dir : director.keySet()) {
            if (director.get(dir) > directorMax) {
                directorMax = director.get(dir);
                directorName = dir;
            }
        }
        System.out.println("Max director: " + directorMax + " it is " + directorName);
        System.out.println("Total director: " + director.size());
    }

    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> answer = new ArrayList<EfficientRater>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord rec : fr.getCSVParser()) {
            boolean there = false;
            for (EfficientRater rater1 : answer) {
                if (rater1.getID().equals(rec.get("rater_id"))) {
                    rater1.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
                    there = true;
                    break;
                }
            }
            if (!there && !rec.get("rater_id").isBlank()) {
                EfficientRater rater = new EfficientRater(rec.get("rater_id"));
                rater.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
                answer.add(rater);
            }

        }
        return answer;
    }

    public void testLoadRaters() {
        ArrayList<EfficientRater> raters = loadRaters("data/ratings.csv");
        ArrayList<String> movieRated = new ArrayList<String>();
        String rater_id = "193";
        int max = 0;
        String maxId = "";
        String movieId = "1798709";
        int movieIdRater = 0;
        for (EfficientRater rater : raters) {
//            System.out.println(rater.getID() + ": " + rater.numRatings());
            if (rater.getID().equals(rater_id)) {
                System.out.println(rater.getID() + ": " + rater.numRatings() + " " + rater.getItemsRated());
            }
            if (rater.numRatings() > max) {
                max = rater.numRatings();
                maxId = rater.getID();
            }
            if (rater.getItemsRated().contains(movieId)) {
                movieIdRater++;
            }
            for (String mov : rater.getItemsRated()) {
                if (!movieRated.contains(mov)) {
                    movieRated.add(mov);
                }
            }
        }
        System.out.println("Raters: " + raters.size());
        System.out.println("Max rating by " + maxId + " has rated " + max + " movie");
        System.out.println(movieId + " has " + movieIdRater + " rater");
        System.out.println("Movie that had been rated: " + movieRated.size() + " there are " + movieRated);
    }
}
