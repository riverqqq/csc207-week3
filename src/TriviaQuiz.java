import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * A simple Java program for fetching trivia quiz questions from an API.
 */
public class TriviaQuiz {
    // The URL of the trivia quiz API with the token
    private final static String API_URL = "https://opentdb.com/api.php?amount=5&token=" + System.getenv("YOURTOKENHERE");

    /**
     * The main entry point of the program.
     *
     * @param args The command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        getQuestions();
    }

    /**
     * Fetches trivia quiz questions from the API and prints them to the console.
     */
    public static void getQuestions() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);

            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                JSONArray results = responseBody.getJSONArray("results");
                for (Object question : results) {
                    System.out.println(question);
                }
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}