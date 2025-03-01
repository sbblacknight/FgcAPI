import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class PlayerCharacterList {
    public static void main(String[] args) throws IOException, InterruptedException {
        String baseURL = "https://puddle.farm/api/player/";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please type below your Rating Update User ID: ");
        String userID = scanner.nextLine();

        String finalURL = baseURL + userID;

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
            URI.create(finalURL)
        ).GET().build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        // Parse the JSON response
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(response.body(), User.class);

        // Display the user's name and characters they play
        System.out.println("User's Name: " + user.getName());
        System.out.println("Characters played:");
        for (Rating rating : user.getRatings()) {
            System.out.println("- " + rating.getCharacter());
        }
    }
}

class User {
    private long id;
    private String name;
    private List<Rating> ratings;
    private String platform;
    private String status;
    private int top_global;
    private List<String> tags;

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Rating> getRatings() { return ratings; }
    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getTop_global() { return top_global; }
    public void setTop_global(int top_global) { this.top_global = top_global; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}

class Rating {
    private double rating;
    private double deviation;
    private String char_short;
    private String character;
    private int match_count;
    private int top_char;
    private TopDefeated top_defeated;
    private TopRating top_rating;

    // Getters and setters
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public double getDeviation() { return deviation; }
    public void setDeviation(double deviation) { this.deviation = deviation; }
    public String getChar_short() { return char_short; }
    public void setChar_short(String char_short) { this.char_short = char_short; }
    public String getCharacter() { return character; }
    public void setCharacter(String character) { this.character = character; }
    public int getMatch_count() { return match_count; }
    public void setMatch_count(int match_count) { this.match_count = match_count; }
    public int getTop_char() { return top_char; }
    public void setTop_char(int top_char) { this.top_char = top_char; }
    public TopDefeated getTop_defeated() { return top_defeated; }
    public void setTop_defeated(TopDefeated top_defeated) { this.top_defeated = top_defeated; }
    public TopRating getTop_rating() { return top_rating; }
    public void setTop_rating(TopRating top_rating) { this.top_rating = top_rating; }
}

class TopDefeated {
    private String timestamp;
    private long id;
    private String name;
    private String char_short;
    private double value;
    private double deviation;

    // Getters and setters
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getChar_short() { return char_short; }
    public void setChar_short(String char_short) { this.char_short = char_short; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public double getDeviation() { return deviation; }
    public void setDeviation(double deviation) { this.deviation = deviation; }
}

class TopRating {
    private String timestamp;
    private double value;
    private double deviation;

    // Getters and setters
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public double getDeviation() { return deviation; }
    public void setDeviation(double deviation) { this.deviation = deviation; }
}
