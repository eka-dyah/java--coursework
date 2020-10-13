import java.util.ArrayList;

public class RecommendationRunner implements Recommender {
    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> answer = new ArrayList<String>();
        int count = 0;
        for (String id : MovieDatabase.filterBy(new TrueFilter())) {
            if (count >= 10) {
                break;
            }
            count++;
            answer.add(id);
        }
        return answer;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, 50, 5);

        if (ratings.size() == 0) {
            System.out.println("Try again");
            return;
        }

        String sb = "";
        int count = 0;
        for (Rating rating : ratings) {
            if (count >= 10) {
                break;
            }
            sb += "<tr>" +
                    "<td>" + MovieDatabase.getTitle(rating.getItem()) + "</td>" +
                    "<td>" + MovieDatabase.getGenres(rating.getItem()) + "</td>" +
                    "<td>" + MovieDatabase.getMinutes(rating.getItem()) + "</td>" +
                    "</tr>";

            count++;
        }

        String headTable = "<tr>" +
                "<th>Title</th>" +
                "<th>Genre</th>" +
                "<th>Length (min)</th>" +
                "</tr>";
        System.out.println("<style>td,th {border: 1px solid black; text-align: center; padding: 5px;}</style>");
        System.out.println("<table>" + headTable + sb + "</table>");
    }
}
