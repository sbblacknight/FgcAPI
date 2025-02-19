import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.Scanner;

//Learn how to parse JSON in java

public class Practice {
    //Here is URL to check https://puddle.farm/api/player/240709011949023356
    //READ THROUGH API.YOML to see if there's anything else I can do
    public static void main(String[] args) throws IOException, InterruptedException{

        String baseURL = "https://puddle.farm/api/player/";
        //Maybe find way to display info based on name, maybe make another request to find ID from name
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please type below your Rating Update User ID: ");
        String userID = scanner.nextLine();

        String finalURL = baseURL + userID;

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
        URI.create(finalURL)
        ).GET()
        .build(); 

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        //response.body is the string of info
        //add a way to only display first line of information, use Puddlefarm git as example
        // for(int i = 0; i <response.body().length(); i++ ){
        // }
        
        System.out.println(response.body());
        System.out.println("\n");
        System.out.println(response.headers().map());
        System.out.println("\n");


        //Asynchronus response: Runs this while other parts are still running
        // CompletableFuture<HttpResponse<String>> responseAsync = httpClient.sendAsync(request, BodyHandlers.ofString());

        // responseAsync.thenAccept(res -> System.out.println(res.body()));
        // responseAsync.join();
    }
}
