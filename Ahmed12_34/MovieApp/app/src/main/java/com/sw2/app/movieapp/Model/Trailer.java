package com.sw2.app.movieapp.Model;

/**
 * Created by mahmuod nasr on 11/22/2016.
 */

public class Trailer {
    private String name;



    private String movieId;
    private String key;

    public Trailer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
