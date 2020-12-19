
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull", "ratings");
        System.out.println("Total number of movies : " + secondRatings.getMovieSize());
        System.out.println("Total number of raters : " + secondRatings.getRaterSize());

        int minRatingsCount = 12;
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(minRatingsCount);
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
        }
        System.out.println("There are " + averageRatings.size() + " movies with " +
            minRatingsCount + " or more ratings");
    }

    public void getAverageRatingOneMovie() {
        SecondRatings secondRatings = new SecondRatings ("ratedmoviesfull", "ratings");

        String title = "Vacation";
        int minRatingsCount = 1;

        String movieID = secondRatings.getID(title);
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(minRatingsCount);
        for (Rating rating : averageRatings) {
            if (rating.getItem().equals(movieID)) {
                System.out.println("Movie: " + title);
                System.out.println("Average Rating: " + rating.getValue());
            }
        }
    }
}
