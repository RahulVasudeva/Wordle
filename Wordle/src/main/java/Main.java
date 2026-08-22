import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String Args[]){
        try {
            while (true) {
                String guess = IO.readln("\nEnter your guess: ");
                if (guess.equals("q")) break;
                Wordle wd = new Wordle(guess);
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("https://freedictionaryapi.com/api/v1/entries/en/"+guess))
                        .GET()
                        .build();
                HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
                String body = response.body();
                IO.println(body);
                wd.checker();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
