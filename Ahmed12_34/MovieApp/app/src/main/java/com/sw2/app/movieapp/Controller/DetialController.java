package com.sw2.app.movieapp.Controller;

import android.content.Context;

import com.sw2.app.movieapp.Model.Comment;
import com.sw2.app.movieapp.Model.CommentOperations;
import com.sw2.app.movieapp.Model.LikeOperations;
import com.sw2.app.movieapp.Model.Movie;
import com.sw2.app.movieapp.Model.MovieOperations;
import com.sw2.app.movieapp.Model.Person;
import com.sw2.app.movieapp.Model.Trailer;
import com.sw2.app.movieapp.Model.TrailerOperations;

import java.util.List;

/**
 * Created by mahmuod nasr on 11/22/2016.
 */

public class DetialController {
    Movie movie;
    Person person;
    List<Trailer> Trailers;
    MovieOperations movieOperations;
    TrailerOperations trailerOperations;
    CommentOperations commentOperations;
    LikeOperations likeOperations = new LikeOperations();

    public DetialController(Context context){
        movieOperations  = new MovieOperations(context);
        trailerOperations  = new TrailerOperations(context);
        commentOperations  = new CommentOperations(context);
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }


    //Favorite Feature
    public Boolean isFavorite() {
        //MovieOperations movieOperations = new MovieOperations();//////
        return movieOperations.read(String.valueOf(String.valueOf(movie.getId())));

    }

    public void unfavored() {
        movieOperations.delete(String.valueOf(movie.getId()));
    }

    public void favorite() {
        movieOperations.insert(movie);
    }
    public void setTrailers(List<Trailer> trailers){
        Trailers = trailers;
    }
public List<Trailer> getTrailers(){
    return Trailers;
}

    //Like Feature
    public Boolean isLike() {
       /* if (likeOperations.Read(String.valueOf(movie.getId())))
            return true;
        else
            return false;*/
        return true;
    }

    public void unLiked() {
       // likeOperations.delete(movie.getId(), person.getId());
    }

    public void like() {

        //likeOperations.insert(movie.getId(), person.getId());
    }
/*
    //Watch Later Feature
    public Boolean isAddedToWatchLater() {
        if (likeOperations.read(movie.getId()))
            return true;
        else
            return false;
    }
    public void unLiked() {
        likeOperations.delete(movie.getId(), person.getId());
    }

    public void like() {
        likeOperations.insert(movie.getId(), person.getId());
    }
*/


   /* public List<Trailer> getTraliers() {
        return (List<Trailer>) (Object)trailerOperations.readAll();
    }
*/
    public List<Comment> getComments() {
        return (List<Comment>) (Object) commentOperations.readAll();
    }
}
