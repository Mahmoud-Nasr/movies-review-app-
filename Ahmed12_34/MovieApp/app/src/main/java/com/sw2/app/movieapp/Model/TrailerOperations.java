package com.sw2.app.movieapp.Model;

import android.content.Context;
import android.database.Cursor;

import com.sw2.app.movieapp.Interface.Operations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahmuod nasr on 11/23/2016.
 */

public class TrailerOperations implements Operations {
    Database database;
    Trailer trailer;
    public TrailerOperations(Context params){
        database=Database.getdBconnection(params);
    }
    @Override
    public void insert(Object o) {
        trailer=(Trailer)o;
        String query =String.format("INSERT INTO Trailer VALUES ("+trailer.getKey()+","+trailer.getMovieId()+","+trailer.getName()+")");
        database.Insert(query);

    }

    @Override
    public void update(Object o) {
        trailer=(Trailer)o;

        String query =String.format("UPDATE Trailer" +
                "SET Id = "+trailer.getKey()+", MovieId = "+trailer.getMovieId()+", TrailerName = "+trailer.getName()+" " +
                "WHERE id="+trailer.getKey()+" ");



    }

    @Override
    public Object read( String Id) {
        String Select_query= String.format("SELECT * FROM Trailer WHERE Id =?"+Id+" ");
        Cursor cursor=database.Read(Select_query);
        try{
            if(cursor.moveToFirst()){
                trailer.setKey(cursor.getString(cursor.getColumnIndex("Id")));
                trailer.setMovieId(cursor.getString(cursor.getColumnIndex("MovieId")));

                trailer.setName(cursor.getString(cursor.getColumnIndex("TrailerName")));
                return (Object)trailer;

            }

        }catch (Exception e){}
        finally {
            if (cursor!=null&&!cursor.isClosed()){
                cursor.close();
            }
            return null;
        }
    }

    @Override
    public List<Object> readAll() {
        List<Trailer> result=new ArrayList<>();
        String Select_query= String.format("SELECT * FROM Trailer ");
        Cursor cursor=database.Read(Select_query);
        try{
            if(cursor.moveToFirst()){
                do {
                    Trailer trailer=new Trailer();
                    trailer.setKey(cursor.getString(cursor.getColumnIndex("Id")));
                    trailer.setMovieId(cursor.getString(cursor.getColumnIndex("MovieId")));

                    trailer.setName(cursor.getString(cursor.getColumnIndex("TrailerName")));
                    result.add(trailer);
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

        String delete_string=String.format("DELETE FROM  Trailer"+
                " WHERE Id =? "+Id+" ");
        database.Delete(delete_string);
    }
}
