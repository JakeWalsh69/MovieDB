package controller;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue
    @Column(insertable=false, updatable = false)
    private Long id;
    private boolean adult;
    @Column(insertable=false, updatable = false)
    private String backdrop_path;
    private int budget;
    private int movie_id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    @Column(insertable=false, updatable = false)
    private String poster_path;
    private String release_date;
    private long revenue;
    private int runtime;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;

    public Movie() {
    }

    public Movie(Long id, boolean adult, String backdrop_path, int budget, int movie_id, String imdb_id, String original_language, String original_title, String overview, double popularity, String poster_path, String release_date, long revenue, int runtime, String status, String tagline, String title, boolean video, double vote_average, int vote_count) {
        this.id = id;
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.budget = budget;
        this.movie_id = movie_id;
        this.imdb_id = imdb_id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }
}