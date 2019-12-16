package com.sw2.app.movieapp.Controller;

import android.content.Context;

import com.sw2.app.movieapp.View.BackgroundThread;
import com.sw2.app.movieapp.API.JsonParsing;
import com.sw2.app.movieapp.Model.Movie;
import com.sw2.app.movieapp.Model.MovieOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahmuod nasr on 11/22/2016.
 */

public class PostersController {
    List<Movie> movies;
    JsonParsing jsonParsing;
    BackgroundThread backgroundThread;

    public static List<Movie> movieList = new ArrayList<>();

    Movie movie1, movie2;
    MovieOperations movieOperations;

    public PostersController(Context context) {
        movieOperations = new MovieOperations(context);
    }

    public List<Movie> getMovies(String url) {

        backgroundThread = new BackgroundThread() {
            @Override
            protected void onPostExecute(String stringBuffer) {
                jsonParsing = new JsonParsing();
                movies = jsonParsing.movieParsing(stringBuffer);
            }
        };

        backgroundThread.execute(url);
        return movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Movie> getFavoriteMovies() {

        return (List<Movie>) (Object) movieOperations.readAll();
    }

    public Movie getMovie(int position) {
        return movies.get(position);
    }

    public void setMovies(List<Movie> movieList) {
        movies = movieList;
    }
}
