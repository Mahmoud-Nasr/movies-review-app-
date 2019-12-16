package com.sw2.app.movieapp.Model;

import android.content.Context;
import android.database.Cursor;

import com.sw2.app.movieapp.Interface.Operations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahmuod nasr on 11/23/2016.
 */

public class MovieOperations implements Operations {
    Database database;
    Movie movie;
    public MovieOperations(Context params){
        database=Database.getdBconnection(params);
    }
    @Override
    public void insert(Object o) {
        movie=(Movie)o;
        database.addMovie(movie);

        /*
        String query =String.format("INSERT INTO Movie VALUES ('"+movie.getId()+"','"+movie.getTitle()+"',"
                +movie.getRate()+",'"+movie.getYear()+"','"+movie.getImage()+"','"+movie.getDescription()+"','"+movie.isAdults() +"','" +
                movie.getType()+"')");
        database.Insert(query);*/

    }

    @Override
    public void update(Object o) {
        movie=(Movie)o;

        String query =String.format("UPDATE Movie" +
                "SET Id = "+movie.getId()+", Title = "+movie.getTitle()+", Image = "+movie.getImage()+" " +
                ", Year = "+movie.getYear()+" " +", Description = "+movie.getDescription()+" " +", Type = "+movie.getType()+" " +
                ", Adult = "+movie.isAdults()+" " +
                "WHERE id="+movie.getId()+" ");



    }

    @Override
    public Boolean read( String Id) {
        String Select_query = "SELECT * FROM Movie WHERE Id = "+Integer.parseInt(Id);
        Cursor cursor = database.Read(Select_query);
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    @Override
    public List<Object> readAll() {
        List<Movie> result=new ArrayList<>();
        String Select_query= String.format("SELECT * FROM Movie ");
        Cursor cursor=database.Read(Select_query);
        try{
            if(cursor.moveToFirst()){
                do {
                    Movie movie=new Movie();
                    movie.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                    movie.setAdults(false);

                    movie.setDescription(cursor.getString(cursor.getColumnIndex("Description")));
                    movie.setImage(cursor.getString(cursor.getColumnIndex("Image")));
                    movie.setRate(cursor.getDouble(cursor.getColumnIndex("Rate")));
                    movie.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
                    movie.setYear(cursor.getString(cursor.getColumnIndex("Year")));

                    result.add(movie);
                }while (cursor.moveToNext());
            }

        }catch (Exception e){}
        finally {
            if (cursor!=null&&!cursor.isClosed()){
                cursor.close();
            }
        }
        return (List<Object>)(Object)result;
    }

    @Override
    public void delete(String Id) {

        String delete_string=String.format("DELETE FROM  Movie"+
                " WHERE Id =? "+Id+" ");
        database.Delete(delete_string);
    }
}
