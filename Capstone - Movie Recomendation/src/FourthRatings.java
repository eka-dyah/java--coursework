import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {

    private double getAverageByID(String id, int minimalRaters) {
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        double count = 0;
        double sumRate = 0;
        for (Rater rater : raters) {
            if (rater.getItemsRated().contains(id)) {
                count++;
                sumRate += rater.getRating(id);
            }
        }
        if (minimalRaters <= count) {
            return sumRate / count;
        }
        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> moviesRating = new ArrayList<Rating>();

        for (String movie : movies) {
            double average = getAverageByID(movie, minimalRaters);
            if (average != 0.0) {
                moviesRating.add(new Rating(movie, average));
            }
        }
        return moviesRating;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> answer = new ArrayList<Rating>();
        ArrayList<Rating> rateList = getAverageRatings(minimalRaters);
        for (String movie : MovieDatabase.filterBy(filterCriteria)) {
            for (Rating rate : rateList) {
                if (rate.getItem().equals(movie)) {
                    answer.add(rate);
                }
            }
        }
        return answer;
    }

    private double dotProduct(Rater me, Rater r) {
        double sum = 0;
        for (String id : me.getItemsRated()) {
            if (r.hasRating(id)) {
                sum += (r.getRating(id) - 5) * (me.getRating(id) - 5);
            }
        }
        return sum;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> answer = new ArrayList<Rating>();
        Rater rater = RaterDatabase.getRater(id);

        for (Rater raterNext : RaterDatabase.getRaters()) {
            if (!raterNext.getID().equals(id)) {
                double similar = dotProduct(rater, raterNext);
                answer.add(new Rating(raterNext.getID(), similar));
            }
        }
        Collections.sort(answer, Collections.reverseOrder());
        return answer;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRater) {
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRater, new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRater, Filter filterCriteria) {
        ArrayList<Rating> answer = new ArrayList<Rating>();

        ArrayList<Rating> ratings = getSimilarities(id);

        for (String movieId : MovieDatabase.filterBy(filterCriteria)) {
            int count = 0;
            double weight = 0;
            for (int i=0; i < numSimilarRaters; i++) {
                String raterId = ratings.get(i).getItem();
                if (RaterDatabase.getRater(raterId).hasRating(movieId) &&
                        ratings.get(i).getValue() >= 0) {
                    count ++;
                    weight += (ratings.get(i).getValue() * RaterDatabase.getRater(raterId).getRating(movieId));
                }
            }
            if (count >= minimalRater) {
                answer.add(new Rating(movieId, weight/count));
            }
        }

        Collections.sort(answer, Collections.reverseOrder());

        return answer;
    }
}
