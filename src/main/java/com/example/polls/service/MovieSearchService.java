package com.example.polls.service;

import com.example.polls.model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class MovieSearchService {
    static final String KEY = "k_2ffhm55i";

    public Movie advancedSearchByKeywords() {
        HttpURLConnection connection = null;

        try {
            String movieName = "Medicine" + "%2C" + "Genius";

            //  https://imdb-api.com/API/AdvancedSearch/k_2ffhm55i/?keywords=murder%2Cdeath
            URL url = new URL("https://imdb-api.com/en/API/AdvancedSearch/" + KEY + "/?keywords=" + movieName);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder responce = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                responce.append(line);
                responce.append("\r");
            }
            reader.close();

            JSONObject object = new JSONObject(responce.toString()); // responce == result

            JSONArray recs = object.getJSONArray("results");
            String movieId = "";
            String title = "";
            String releaseYear = "";
            String rating = "";

            for (int i = 0; i < 1; ++i) {
                JSONObject rec = recs.getJSONObject(i);
                releaseYear = rec.getString("description");
                movieId = rec.getString("id");
                title = rec.getString("title");
                rating = rec.getString("imDbRating");
            }

            return new Movie(movieId, title, releaseYear, rating);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
