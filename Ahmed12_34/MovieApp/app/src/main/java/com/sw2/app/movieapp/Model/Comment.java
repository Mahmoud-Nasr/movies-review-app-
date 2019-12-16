package com.sw2.app.movieapp.Model;

/**
 * Created by mahmuod nasr on 11/22/2016.
 */

public class Comment {
    private String author;
    private String content;
    private String User_Id;
    private  int Id;
    private String Date;

    private String Movie_Id;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }




    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMovie_Id() {
        return Movie_Id;
    }

    public void setMovie_Id(String movie_Id) {
        Movie_Id = movie_Id;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
