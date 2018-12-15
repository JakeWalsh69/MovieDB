package controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * This Controller holds all language based search API Queries to The Movie DB API: https://www.themoviedb.org/documentation/api
 * HTTP Requests are created below and retrieve JSON Data from the API.
 * This data can then be placed in a JSONObject and output to the screen and/or stored in the database.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
@ComponentScan({"movie", "repository", "controller"})
public class LanguageSearchController {

    /**
     * The getMovieDataInChosenLanguage() method takes the movie id and preferred language and returns the data for all matches in the chosen language.
     * Example URL: http://localhost:8080/api/movie/id/551/language/f
     * This will return data for the Poseidon Adventure in French.
     */

    @RequestMapping("/movie/id/{id}/language/{language}")
    public String getMovieDataInChosenLanguage(@PathVariable Long id, @PathVariable char language) throws IOException {
        String url = null;
        if (language == 'e') {
            url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US";
        } else if (language == 'f') {
            url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=fr";
        } else if (language == 'g'){
            url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=de";
        } else if (language == 's'){
            url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=es";
        } else if (language == 'i'){
            url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=it";
        }

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("Sending GET Request to URL: \t" + url);
        System.out.println("Returning Response Code: \t\t" + responseCode + "\n");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        System.out.println("===================================================================");
        System.out.println("RAW JSON Data Returned from GET Request");
        System.out.println(response.toString());

        JSONObject jsonResponse = new JSONObject(response.toString());
        System.out.println("===================================================================");
        System.out.println("Result after Reading JSON Response Object");
        System.out.println("Adult: \t\t\t\t\t" + jsonResponse.get("adult"));
        System.out.println("Backdrop Path: \t\t\t" + jsonResponse.get("backdrop_path"));
        System.out.println("Id: \t\t\t\t\t" + jsonResponse.get("id"));
        System.out.println("Name: \t\t\t\t\t" + jsonResponse.get("title"));
        System.out.println("Poster Path: \t\t\t" + jsonResponse.get("poster_path"));
        System.out.println("Budget: \t\t\t\t" + jsonResponse.get("budget"));

        JSONArray genreArray = jsonResponse.getJSONArray("genres");
        System.out.print("Genres: \t\t\t\t");
        for (int i = 0; i < genreArray.length(); i++) {
            System.out.print(genreArray.getJSONObject(i).getString("name"));
            if (i < genreArray.length()-1){
                System.out.print(", ");
            }
        }

        System.out.println("\nHomepage: \t\t\t\t" + jsonResponse.get("homepage"));
        System.out.println("Imdb ID: \t\t\t\t" + jsonResponse.get("imdb_id"));
        System.out.println("Original Language: \t\t" + jsonResponse.get("original_language"));
        System.out.println("Original Title: \t\t" + jsonResponse.get("original_title"));
        System.out.println("Overview: \t\t\t\t" + jsonResponse.get("overview"));
        System.out.println("Popularity: \t\t\t" + jsonResponse.get("popularity"));
        System.out.println("===================================================================");

        JSONArray productionCompaniesArray = jsonResponse.getJSONArray("production_companies");
        System.out.println("Production Companies: \t");
        for (int i = 0; i < productionCompaniesArray.length(); i++){
            System.out.print("Company " + (i+1));
            System.out.println(":\t\t\t\tName: \t\t\t\t" + productionCompaniesArray.getJSONObject(i).get("name"));
            System.out.println("\t\t\t\t\t\tID: \t\t\t\t" + productionCompaniesArray.getJSONObject(i).get("id"));
            System.out.println("\t\t\t\t\t\tOrigin Country: \t" + productionCompaniesArray.getJSONObject(i).get("origin_country"));
        }
        System.out.println("===================================================================");

        JSONArray productionCountriesArray = jsonResponse.getJSONArray("production_countries");
        System.out.println("Production Countries: \t");
        for (int i = 0; i < productionCountriesArray.length(); i++){
            System.out.print("Country " + (i+1));
            System.out.println(":\t\t\t\tISO: \t" + productionCountriesArray.getJSONObject(i).get("iso_3166_1"));
            System.out.println("\t\t\t\t\t\tName: \t" + productionCountriesArray.getJSONObject(i).get("name"));
        }
        System.out.println("===================================================================");
        System.out.println("Release Date: \t\t\t" + jsonResponse.get("release_date"));
        System.out.println("Revenue: \t\t\t\t" + jsonResponse.get("revenue"));
        System.out.println("Runtime: \t\t\t\t" + jsonResponse.get("runtime"));

        JSONArray spokenLanguagesArray = jsonResponse.getJSONArray("spoken_languages");
        System.out.println("Spoken Languages: \t\t");
        for (int i = 0; i < spokenLanguagesArray.length(); i++){
            System.out.print("Language " + (i+1));
            System.out.println(":\t\t\t\tISO: \t" + spokenLanguagesArray.getJSONObject(i).get("iso_639_1"));
            System.out.println("\t\t\t\t\t\tName: \t" + spokenLanguagesArray.getJSONObject(i).get("name"));
        }
        System.out.println("===================================================================");

        System.out.println("Status: \t\t\t\t" + jsonResponse.get("status"));
        System.out.println("Tagline: \t\t\t\t" + jsonResponse.get("tagline"));
        System.out.println("Video: \t\t\t\t\t" + jsonResponse.get("video"));
        System.out.println("Vote Average: \t\t\t" + jsonResponse.get("vote_average"));
        System.out.println("Vote Count: \t\t\t" + jsonResponse.get("vote_count"));

        return response.toString();
    }
}
