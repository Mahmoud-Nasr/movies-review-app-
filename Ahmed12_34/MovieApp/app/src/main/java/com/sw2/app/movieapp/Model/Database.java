package com.sw2.app.movieapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mahmuod nasr on 11/22/2016.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MovieAppDatabase";
    private static final int DATABASE_VERSION = 8;

    // Table Names
    private static final String TABLE_Movie = "Movie";
    private static final String TABLE_Comment = "Comment";
    private static final String TABLE_Trailer = "Trailer";
    private static final String TABLE_Like = "Movie_Like";
    private static final String TABLE_Person = "Person";

    // Movie Table Columns
    private static final String KEY_Movie_ID = "Id";
    private static final String KEY_Movie_Title = "Title";
    private static final String KEY_Movie_Image = "Image";
    private static final String KEY_Movie_Rate = "Rate";
    private static final String KEY_Movie_Year = "Year";
    private static final String KEY_Movie_Description = "Description";
    private static final String KEY_Movie_Type = "Type";
    private static final String KEY_Movie_Adult = "Adult";

    // Comment Table Columns
    private static final String KEY_Comment_Id = "Id";
    private static final String KEY_Comment_Movie_Id_FK = "MovieId";
    private static final String KEY_Comment_User_Id_FK = "UserId";
    private static final String KEY_Comment_Content = "Content";
    private static final String KEY_Comment_Date = "Date";

    // Trailer Table Columns
    private static final String KEY_Trailer_Id = "Id";
    private static final String KEY_Trailer_Movie_Id_FK = "MovieId";
    private static final String KEY_Trailer_TrailerName = "TrailerName";

    // Like Table Columns
    private static final String KEY_Like_Movie_Id_Compsite = "MovieId";
    private static final String KEY_Like_User_Id_Compsite = "UserId";

    // Person Table Columns
    private static final String KEY_Person_Id = "Id";
    private static final String KEY_Person_Username = "Username";
    private static final String KEY_Person_Password = "Password";
    private static final String KEY_Person_Email = "Email";
    private static final String KEY_Person_DateOfBirth = "DateOfBirth";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static Database database;

    public static synchronized Database getdBconnection(Context context) {
        if (database == null) {
            database = new Database(context.getApplicationContext());
        }
        return database;
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Movie_TABLE = "CREATE TABLE " + TABLE_Movie +
                "(" +
                KEY_Movie_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_Movie_Title + " TEXT," +
                KEY_Movie_Rate + " Double," +
                KEY_Movie_Year + " TEXT," +
                KEY_Movie_Image + " TEXT," +
                KEY_Movie_Description + " TEXT," +
                KEY_Movie_Adult + " BOOLEAN," +
                KEY_Movie_Type + " TEXT" +
                ")";

        String CREATE_Comment_TABLE = "CREATE TABLE " + TABLE_Comment +
                "(" +
                KEY_Comment_Id + " Integer PRIMARY KEY," +
                KEY_Comment_Movie_Id_FK + " TEXT REFERENCES " + TABLE_Movie + "," + // Define a foreign key
                KEY_Comment_User_Id_FK + " TEXT REFERENCES " + TABLE_Person + "," + // Define a foreign key
                KEY_Comment_Content + " TEXT," +
                KEY_Comment_Date + " TEXT" +
                ")";

        String CREATE_Trailer_TABLE = "CREATE TABLE " + TABLE_Trailer +
                "(" +
                KEY_Trailer_Id + " TEXT PRIMARY KEY," +
                KEY_Trailer_Movie_Id_FK + " TEXT REFERENCES " + TABLE_Movie + "," + // Define a foreign key
                KEY_Trailer_TrailerName + " TEXT" +
                ")";

        /*String CREATE_Like_TABLE = "CREATE TABLE " + TABLE_Like +
                "(" +
                KEY_Like_Movie_Id_Compsite + " Integer," +
                KEY_Like_User_Id_Compsite + " Integer," +
                "PRIMARY KEY(" + KEY_Like_Movie_Id_Compsite + "," + KEY_Like_User_Id_Compsite + ")"
                ;
*/
        String CREATE_Person_TABLE = "CREATE TABLE " + TABLE_Person +
                "(" +
                KEY_Person_Id + " Integer PRIMARY KEY," +
                KEY_Person_Username + " TEXT," +
                KEY_Person_Password + " TEXT," +
                KEY_Person_Email + " TEXT," +
                KEY_Person_DateOfBirth + " TEXT" +
                ")";


        db.execSQL(CREATE_Movie_TABLE);
        db.execSQL(CREATE_Comment_TABLE);
        db.execSQL(CREATE_Trailer_TABLE);
  //      db.execSQL(CREATE_Like_TABLE);
        db.execSQL(CREATE_Person_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Movie);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Trailer);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Comment);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Like);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Person);
            onCreate(db);
        }
    }


    public void Insert(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues values = new ContentValues();// ???????
        try {
            db.execSQL(sql);


        } catch (Exception ex) {

        }
  //      db.close();
    }

    public void Update(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues values = new ContentValues();// ???????
        try {
            db.execSQL(sql);
        } catch (Exception ex) {

        }
    //    db.close();
    }

    public Cursor Read(String sql) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.rawQuery(sql,null);
        //ContentValues values = new ContentValues();// ???????

//        db.close();
        return cursor;
    }

public void addMovie(Movie movie){
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();

    values.put(KEY_Movie_ID, movie.getId());
    values.put(KEY_Movie_Adult, movie.isAdults());
    values.put(KEY_Movie_Description, movie.getDescription());
    values.put(KEY_Movie_Image, movie.getImage());
    values.put(KEY_Movie_Rate, movie.getRate());
    values.put(KEY_Movie_Title, movie.getTitle());
    values.put(KEY_Movie_Year, movie.getYear());
    values.put(KEY_Movie_Type, movie.getType());

    db.insert(TABLE_Movie, null, values);
    db.close();
}

    public void Delete(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues values = new ContentValues();// ???????
        try {
            db.execSQL(sql);
        } catch (Exception ex) {

        }
      //  db.close();
    }



}








