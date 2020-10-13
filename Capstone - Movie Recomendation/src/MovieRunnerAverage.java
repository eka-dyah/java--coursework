import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
    public void printAverageRatings() {
//        SecondRatings sr = new SecondRatings();
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("Movie size: " + sr.getMovieSize());
        System.out.println("Rater size: " + sr.getRaterSize());

        ArrayList<Rating> avrg = sr.getAverageRatings(12);
        Collections.sort(avrg);
        for (Rating rating : avrg) {
            System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
        }
        System.out.println("Size average array: " + avrg.size());
    }

    public void getAverageRatingOneMovie() {
//        SecondRatings sr = new SecondRatings();
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String title = "Vacation";
        ArrayList<Rating> avrg = sr.getAverageRatings(0);
        Collections.sort(avrg);
        for (Rating rating : avrg) {
            if (sr.getID(title).equals(rating.getItem())) {
                System.out.println(title + " " + rating.getValue());
                return;
            }
        }
    }

}
