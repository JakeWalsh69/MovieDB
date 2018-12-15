package controller;

import model.Genre;
import model.ProductionCompanies;
import model.ProductionCountries;
import model.SpokenLanguages;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import repository.MovieRespository;
import repository.UserRepository;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This Controller holds all generic search API Queries to The Movie DB API: https://www.themoviedb.org/documentation/api
 * HTTP Requests are created below and retrieve JSON Data from the API.
 * This data can then be placed in a JSONObject and output to the screen and/or stored in the database.
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@ComponentScan({"movie", "repository", "controller"})
public class GenericSearchController {

    @Autowired
    private MovieRespository movieRespository;

    Map<Long, Movie> moviesList = new HashMap<Long, Movie>();

    /**
     * The getMovieDataFromID() method will return JSON Data from a movie based on it's ID within The Movie DB API.
     * A GET Request is used to retrieve the data using an API Key to gain access to the external API.
     * Example Request: http://localhost:8080/api/movie/id/551
     * The above example returns data for The Poseidon Adventure.
     */
    @RequestMapping(value = "/movie/id/{id}", consumes = MediaType.ALL_VALUE)
    public String getMovieDataFromID(@PathVariable Long id) throws IOException {
        String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=383b0d4a4c7c80d01138a5ad8902b121";
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

        List<Genre> genres = new ArrayList<>();
        List<ProductionCompanies> productionCompanies = new ArrayList<>();
        List<ProductionCountries> productionCountries = new ArrayList<>();
        List<SpokenLanguages> spokenLanguages = new ArrayList<>();

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
            Genre g = new Genre((Integer) genreArray.getJSONObject(i).get("id"), (String) genreArray.getJSONObject(i).get("name"));
            genres.add(g);
            System.out.print(genreArray.getJSONObject(i).getString("name"));
            if (i < genreArray.length() - 1) {
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
        for (int i = 0; i < productionCompaniesArray.length(); i++) {
            ProductionCompanies productionCompany = new ProductionCompanies(productionCompaniesArray.getJSONObject(i).getLong("id"), (String) productionCompaniesArray.getJSONObject(i).get("name"), (String) productionCompaniesArray.getJSONObject(i).get("origin_country"));
            productionCompanies.add(productionCompany);
            System.out.print("Company " + (i + 1));
            System.out.println(":\t\t\t\tName: \t\t\t\t" + productionCompaniesArray.getJSONObject(i).get("name"));
            System.out.println("\t\t\t\t\t\tID: \t\t\t\t" + productionCompaniesArray.getJSONObject(i).get("id"));
            System.out.println("\t\t\t\t\t\tOrigin Country: \t" + productionCompaniesArray.getJSONObject(i).get("origin_country"));
        }
        System.out.println("===================================================================");

        JSONArray productionCountriesArray = jsonResponse.getJSONArray("production_countries");
        System.out.println("Production Countries: \t");
        for (int i = 0; i < productionCountriesArray.length(); i++) {
            ProductionCountries productionCountry = new ProductionCountries(productionCountriesArray.getJSONObject(i).getString("iso_3166_1"), (String) productionCountriesArray.getJSONObject(i).get("name"));
            productionCountries.add(productionCountry);
            System.out.print("Country " + (i + 1));
            System.out.println(":\t\t\t\tISO: \t" + productionCountriesArray.getJSONObject(i).get("iso_3166_1"));
            System.out.println("\t\t\t\t\t\tName: \t" + productionCountriesArray.getJSONObject(i).get("name"));
        }
        System.out.println("===================================================================");
        System.out.println("Release Date: \t\t\t" + jsonResponse.get("release_date"));
        System.out.println("Revenue: \t\t\t\t" + jsonResponse.get("revenue"));
        System.out.println("Runtime: \t\t\t\t" + jsonResponse.get("runtime"));

        JSONArray spokenLanguagesArray = jsonResponse.getJSONArray("spoken_languages");
        System.out.println("Spoken Languages: \t\t");
        for (int i = 0; i < spokenLanguagesArray.length(); i++) {
            SpokenLanguages spokenLanguage = new SpokenLanguages((String) spokenLanguagesArray.getJSONObject(i).get("iso_639_1"), (String) spokenLanguagesArray.getJSONObject(i).get("name"));
            spokenLanguages.add(spokenLanguage);
            System.out.print("Language " + (i + 1));
            System.out.println(":\t\t\t\tISO: \t" + spokenLanguagesArray.getJSONObject(i).get("iso_639_1"));
            System.out.println("\t\t\t\t\t\tName: \t" + spokenLanguagesArray.getJSONObject(i).get("name"));
        }
        System.out.println("===================================================================");

        System.out.println("Status: \t\t\t\t" + jsonResponse.get("status"));
        System.out.println("Tagline: \t\t\t\t" + jsonResponse.get("tagline"));
        System.out.println("Video: \t\t\t\t\t" + jsonResponse.get("video"));
        System.out.println("Vote Average: \t\t\t" + jsonResponse.get("vote_average"));
        System.out.println("Vote Count: \t\t\t" + jsonResponse.get("vote_count"));

        movieRespository.save(new Movie(movieRespository.count()+1, jsonResponse.getBoolean("adult"), jsonResponse.getString("backdrop_path"), jsonResponse.getInt("budget"), jsonResponse.getInt("id"), jsonResponse.getString("imdb_id"), jsonResponse.getString("original_language"), jsonResponse.getString("original_title"), jsonResponse.getString("overview"), jsonResponse.getDouble("popularity"), jsonResponse.getString("poster_path"), jsonResponse.getString("release_date"), jsonResponse.getInt("revenue"), jsonResponse.getInt("runtime"), jsonResponse.getString("status"), jsonResponse.getString("tagline"), jsonResponse.getString("title"), jsonResponse.getBoolean("video"), jsonResponse.getDouble("vote_average"), jsonResponse.getInt("vote_count")));

        return response.toString();
    }

    /**
     * The getTvShowDataById() method finds a tv show based on id and is similar to the getMovieDataById() method.
     * Example url: http://localhost:8080/api/tv/id/60574
     * The above example will return all data for the tv show: Peaky Blinders.
     */
    @RequestMapping("tv/id/{id}")
    public String getTvShowDataById(@PathVariable Long id) throws IOException {
        String url = "https://api.themoviedb.org/3/tv/" + id + "?api_key=383b0d4a4c7c80d01138a5ad8902b121";

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
        System.out.println("Name: \t\t\t\t\t" + jsonResponse.get("name"));

        JSONArray createdByArray = jsonResponse.getJSONArray("created_by");
        System.out.println("Created by: \t");
        for (int i = 0; i < createdByArray.length(); i++) {
            System.out.println("\t\tID: \t\t\t" + createdByArray.getJSONObject(i).get("id"));
            System.out.println("\t\tCredit ID: \t\t" + createdByArray.getJSONObject(i).get("credit_id"));
            System.out.println("\t\tName: \t\t\t" + createdByArray.getJSONObject(i).get("name"));
            System.out.println("\t\tProfile Path: \t" + createdByArray.getJSONObject(i).get("profile_path"));
        }
        System.out.println("===================================================================");
        System.out.println("Episode run time: \t\t" + jsonResponse.get("episode_run_time"));
        System.out.println("First air date: \t\t" + jsonResponse.get("first_air_date"));

        JSONArray genreArray = jsonResponse.getJSONArray("genres");
        System.out.println("Genres: \t");
        for (int i = 0; i < genreArray.length(); i++) {
            System.out.println("\t\tID: \t\t\t" + genreArray.getJSONObject(i).get("id"));
            System.out.println("\t\tGenre: \t\t\t" + genreArray.getJSONObject(i).get("name"));
        }
        System.out.println("===================================================================");
        System.out.println("Homepage: \t\t\t\t" + jsonResponse.get("homepage"));
        System.out.println("ID: \t\t\t\t\t" + jsonResponse.get("id"));
        System.out.println("In Production: \t\t\t" + jsonResponse.get("in_production"));
        System.out.println();

        return response.toString();
    }

    /**
     * The searchForMovieBasedOnKeyword() method uses a specific keyword and returns all movie data corresponding to it.
     * Example URL: http://localhost:8080/api/movie/keyword/ace
     * This will return all data related to the word 'ace'.
     */
    @RequestMapping("movie/keyword/{keyword}")
    public String searchForMovieBasedOnKeyword(@PathVariable String keyword) throws IOException {
        String url = "https://api.themoviedb.org/3/search/movie?query=" + keyword + "&api_key=383b0d4a4c7c80d01138a5ad8902b121";

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

        return response.toString();
    }

    /**
     * The searchForTvShowBasedOnKeyword() method uses a specific keyword and returns all tv show data corresponding to it.
     * Example URL: http://localhost:8080/api/tv/keyword/friends
     * This will return all data related to the word 'friends'.
     */
    @RequestMapping("tv/keyword/{keyword}")
    public String searchForTvShowBasedOnKeyword(@PathVariable String keyword) throws IOException {
        String url = "https://api.themoviedb.org/3/search/tv?query=" + keyword + "&api_key=383b0d4a4c7c80d01138a5ad8902b121";

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

        return response.toString();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/all")
    public List<Movie> getMovies() {

        List<Movie> movieList = moviesList.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());

        return movieList;
    }

    /**
     * All Database related Requests
     */
}
