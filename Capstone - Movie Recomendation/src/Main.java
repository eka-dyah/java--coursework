public class Main {
    public static void main(String[] args) {
        FirstRatings firstRatings = new FirstRatings();
//        firstRatings.testLoadMovies();
//        firstRatings.testLoadRaters();
        MovieRunnerAverage mra = new MovieRunnerAverage();
//        mra.getAverageRatingOneMovie();
//        mra.printAverageRatings();
        MovieRunnerWithFilters mraf = new MovieRunnerWithFilters();
//        mraf.printAverageRatings();
//        mraf.printAverageRatingsByYear();
//        mraf.printAverageRatingsByGenre();
//        mraf.printAverageRatingsByMinutes();
//        mraf.printAverageRatingsByDirector();
//        mraf.printAverageRatingsByYearAfterAndGenre();
//        mraf.printAverageRatingsByDirectorsAndMinutes();
        MovieRunnerSimilarRatings mrsr = new MovieRunnerSimilarRatings();
//        mrsr.printAverageRatingsByYearAfterAndGenre();
//        mrsr.printSimilarRatings();
//        mrsr.printSimilarRatingsByDirector();
//        mrsr.printSimilarRatingsByGenreAndMinutes();
//        mrsr.printSimilarRatingsByYearAfterAndMinutes();
        RecommendationRunner rr = new RecommendationRunner();
        rr.printRecommendationsFor("71");

    }
}
