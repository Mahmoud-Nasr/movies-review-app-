package com.sw2.app.movieapp.Model;

import java.io.Serializable;

/**
 * Created by mahmuod nasr on 11/22/2016.
 */

public class Movie implements Serializable{
    private int Id;
    private String title;
    private String image;

    private String year;
    private Double rate;
    private String description;

    public boolean isAdults() {
        return Adults;
    }

    public void setAdults(boolean adults) {
        Adults = adults;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private boolean Adults;
    private String type;


    public Movie(int id, String title, String image, String year, Double rate, String description) {
        Id = id;
        this.title = title;
        this.image = image;
        this.year = year;
        this.rate = rate;
        this.description = description;
    }
    public Movie() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

}
