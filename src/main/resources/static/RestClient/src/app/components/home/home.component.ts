import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css', '../../../../node_modules/bootstrap/dist/css/bootstrap.min.css']
})
export class HomeComponent {

  id: string = "";
  keyword: string = "";
  response: any;
  require: any;
  data: any;
  leo: any;
  fullname: any;
  fname: any;
  lname: any;
  genre: any;
  genres: any;
  constructor(private http: HttpClient){

  }

  ngOnInit() {
    this.http.get('https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data);
    })
  }

  searchByKeywordInEnglish(){
    this.http.get('https://api.themoviedb.org/3/search/movie?query=' + this.keyword + '&api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data)
    });
  }

  searchByKeywordInFrench(){
    this.http.get('https://api.themoviedb.org/3/search/movie?query=' + this.keyword + '&api_key=383b0d4a4c7c80d01138a5ad8902b121&language=fr')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data)
    });
  }

  searchByKeywordInGerman(){
    this.http.get('https://api.themoviedb.org/3/search/movie?query=' + this.keyword + '&api_key=383b0d4a4c7c80d01138a5ad8902b121&language=de')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data)
    });
  }

  searchByKeywordInSpanish(){
    this.http.get('https://api.themoviedb.org/3/search/movie?query=' + this.keyword + '&api_key=383b0d4a4c7c80d01138a5ad8902b121&language=es')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data)
    });
  }

  searchByKeywordInItalian(){
    this.http.get('https://api.themoviedb.org/3/search/movie?query=' + this.keyword + '&api_key=383b0d4a4c7c80d01138a5ad8902b121&language=it')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data)
    });
  }

  mostPopularMovies(){
    this.http.get('https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data);
    });
  }

  mostPopularMoviesInEnglish(){
    this.http.get('https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data);
    });
  }

  mostPopularMoviesInFrench(){
    this.http.get('https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=fr&sort_by=popularity.desc&include_adult=false&include_video=false&page=1')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data);
    });
  }

  mostPopularMoviesInGerman(){
    this.http.get('https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=de&sort_by=popularity.desc&include_adult=false&include_video=false&page=1')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data);
    });
  }

  mostPopularMoviesInSpanish(){
    this.http.get('https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=es&sort_by=popularity.desc&include_adult=false&include_video=false&page=1')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data);
    });
  }

  mostPopularMoviesInItalian(){
    this.http.get('https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=it&sort_by=popularity.desc&include_adult=false&include_video=false&page=1')
    .subscribe((data) => {
      this.data = data;

      JSON.stringify(data);

      console.log(data);
    });
  }

  searchByName(){
    this.http.get('https://api.themoviedb.org/3/search/person?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&page=1&include_adult=false&search_type=ngram&query=' + this.fname + "+" + this.lname)
    .subscribe((fullname) => {
      this.fullname = fullname;

      console.log(fullname);
    });
  }

  searchByGenreAction(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=12&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreAdventure(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=12&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreAnimation(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=16&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreComedy(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=35&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreCrime(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=80&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreDrama(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=18&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreFamily(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=10751&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreFantasy(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=14&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreHistory(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=36&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreMusic(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=10402&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreMystery(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=9648&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreRomance(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=10749&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreScienceFiction(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=878&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreTVMovie(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=10770&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreThriller(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=53&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreWar(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=10752&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreWestern(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=37&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

  searchByGenreHorror(){
    this.http.get("https://api.themoviedb.org/3/discover/movie?api_key=383b0d4a4c7c80d01138a5ad8902b121&language=en-US&sort_by=popularity.desc&with_genres=27&page=1")
    .subscribe((genre) => {
      this.genre = genre;

      console.log(genre);
    });
  }

}
