import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class TriviaQuiz {
    private final static String API_URL = "https://opentdb.com/api.php?amount=5";


    public static void main(String[] args) {
        getQuestions();
    }

    public static void getQuestions(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);

            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()){
                JSONArray results = responseBody.getJSONArray("results");
                for (Object question : results){
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
