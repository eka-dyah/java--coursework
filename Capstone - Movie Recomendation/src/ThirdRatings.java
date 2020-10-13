import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }

    public ThirdRatings (String ratingFile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingFile);

    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters) {
        double count = 0;
        double sumRate = 0;
        for (EfficientRater rater : myRaters) {
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
}
