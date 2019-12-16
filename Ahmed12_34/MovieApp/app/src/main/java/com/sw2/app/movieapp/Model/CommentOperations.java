package com.sw2.app.movieapp.Model;

import android.content.Context;
import android.database.Cursor;

import com.sw2.app.movieapp.Interface.Operations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahmuod nasr on 11/23/2016.
 */

public class CommentOperations implements Operations {
    Database database;
    Comment comment;
    public CommentOperations(Context params){
        database=Database.getdBconnection(params);
    }

    @Override
    public void insert(Object o) {
        comment=(Comment)o;
        String query =String.format("INSERT INTO Movie VALUES ("+comment.getId()+","+comment.getMovie_Id()+","
                +comment.getUser_Id()+","+comment.getContent()+","+comment.getDate()+")");
        database.Insert(query);

    }

    @Override
    public void update(Object o) {
        comment=(Comment)o;

        String query =String.format("UPDATE Movie" +
                "SET Id = "+comment.getId()+", MovieId = "+comment.getMovie_Id()+", UserId = "+comment.getUser_Id()+" " +
                ", Content = "+comment.getContent()+" " +", Date = "+comment.getDate()+
                "WHERE id="+comment.getId()+" ");

database.Update(query);

    }

    @Override
    public Object read( String Id) {
        String Select_query= String.format("SELECT * FROM Movie WHERE Id =?"+Id+" ");
        Cursor cursor=database.Read(Select_query);
        try{
            if(cursor.moveToFirst()){
                comment.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                comment.setDate(cursor.getString(cursor.getColumnIndex("Date")));
                comment.setContent(cursor.getString(cursor.getColumnIndex("Content")));
                comment.setMovie_Id(cursor.getString(cursor.getColumnIndex("MovieId")));
                comment.setUser_Id(cursor.getString(cursor.getColumnIndex("UserId")));
                return (Object)comment;

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
        List<Comment> result=new ArrayList<>();
        String Select_query= String.format("SELECT * FROM Movie ");
        Cursor cursor=database.Read(Select_query);
        try{
            if(cursor.moveToFirst()){
                do {
                    comment.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                    comment.setDate(cursor.getString(cursor.getColumnIndex("Date")));
                    comment.setContent(cursor.getString(cursor.getColumnIndex("Content")));
                    comment.setMovie_Id(cursor.getString(cursor.getColumnIndex("MovieId")));
                    comment.setUser_Id(cursor.getString(cursor.getColumnIndex("UserId")));

                    result.add(comment);
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
