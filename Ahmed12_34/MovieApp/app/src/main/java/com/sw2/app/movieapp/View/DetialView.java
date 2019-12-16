package com.sw2.app.movieapp.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sw2.app.movieapp.API.JsonParsing;
import com.sw2.app.movieapp.Adapters.CommentAdapter;
import com.sw2.app.movieapp.Adapters.PostersAdapter;
import com.sw2.app.movieapp.Adapters.TrailerAdapter;
import com.sw2.app.movieapp.Controller.DetialController;
import com.sw2.app.movieapp.Model.Movie;
import com.sw2.app.movieapp.Model.Trailer;
import com.sw2.app.movieapp.R;

import java.util.List;

/**
 * Created by mahmuod nasr on 11/22/2016.
 */

public class DetialView extends AppCompatActivity {
    DetialController controller ;
    ImageView favoriteImage;
    ImageView likeImage;
    ListView trailer_listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detial_layout);
        controller  = new DetialController(getApplicationContext());
        Intent intent = getIntent();
        controller.setMovie((Movie) intent.getSerializableExtra("movieObject"));
        updateDetail(controller.getMovie());
        favoriteImage = (ImageView) findViewById(R.id.favoriteImage);

        favoriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controller.isFavorite()) {
                    controller.unfavored();
                    Toast.makeText(getApplicationContext(), "UnFavored", Toast.LENGTH_SHORT).show();
                    favoriteImage.setImageResource(R.drawable.hnd5a);
                } else {
                    controller.favorite();
                    Toast.makeText(getApplicationContext(), "Favorite", Toast.LENGTH_SHORT).show();
                    favoriteImage.setImageResource(R.drawable.toecu);
                }
            }
        });

        likeImage = (ImageView) findViewById(R.id.likeImage);
        likeImage.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             if (controller.isLike()) {
                                             } else {
                                             }
                                         }
                                     }
            );

           trailer_listView = (ListView) findViewById(R.id.trailer_list);
        BackgroundThread backgroundThread = new BackgroundThread() {
            @Override
            protected void onPostExecute(String stringBuffer) {
                JsonParsing jsonParsing = new JsonParsing();
                List<Trailer> trailerList = jsonParsing.TrailerParseJson(stringBuffer);
                controller.setTrailers(trailerList);
                TrailerAdapter trailerAdapter = new TrailerAdapter(getApplicationContext(), R.layout.trailer_item, trailerList);
                trailer_listView.setAdapter(trailerAdapter);
            }
        };
        int id = controller.getMovie().getId();
        backgroundThread.execute("https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=e2a5747acc64209b26d53a345f43fcc1&language=en-US");

            trailer_listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

            {
                @Override
                public void onItemClick (AdapterView < ? > parent, View view,int position, long id){

                String url = "https://www.youtube.com/watch?v=" + controller.getTrailers().get(position).getKey();
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(intent);
            }
            }

            );

            ListView comment_listView = (ListView) findViewById(R.id.comment_list);
            CommentAdapter commentAdapter = new CommentAdapter(getApplicationContext(), R.layout.comment_item, controller.getComments());
            comment_listView.setAdapter(commentAdapter);
/*
        Database database = new Database(getApplicationContext());
        Button favButton = (Button) findViewById(R.id.fav_button);
        if (database.readMovie(movieObject.getId()) == null) {
            favButton.setText(R.string.fav);
        } else {
            favButton.setText(R.string.unfav);
        }
        favButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Database database = new Database(getApplicationContext());
                Button favButton = (Button) findViewById(R.id.fav_button);
                if (database.readMovie(movieObject.getId()) == null) {
                    favButton.setText(R.string.unfav);
                    database.createMovie(movieObject);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    favButton.setText(R.string.fav);
                    database.deleteMovie(movieObject.getId());
                    Toast toast = Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        */
        /*
        trailer_listView = (ListView) findViewById(R.id.trailer_list);

        Background background = new Background() {
            @Override
            protected void onPostExecute(String stringBuffer) {
                JsonParsing jsonParsing = new JsonParsing();

                trailerList = jsonParsing.TrailerParseJson(stringBuffer);
                trailerAdapter = new TrailerAdapter(getApplicationContext(), R.layout.trailer_item, trailerList);
                trailer_listView.setAdapter(trailerAdapter);
            }
        };
        background.execute("https://api.themoviedb.org/3/movie/" + movieObject.getId() + "/videos?api_key=dc803b5dbdfccad4ec7f127673c2821b&language=en-US");

        trailer_listView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        setListViewHeightBasedOnChildren(trailer_listView);

        trailer_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String url = "https://www.youtube.com/watch?v=" + trailerList.get(position).getKey();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        */
        }

    public void updateDetail(Movie movieObject) {
        TextView titleTxTView = (TextView) findViewById(R.id.textview_title);
        titleTxTView.setText(movieObject.getTitle().toString());

        ImageView posterImageView = (ImageView) findViewById(R.id.imageView_poster);
        Picasso.with(this).load(movieObject.getImage()).into(posterImageView);

        TextView yearTxTView = (TextView) findViewById(R.id.textview_year);
        yearTxTView.setText(movieObject.getYear() + "");

        TextView rateTxTView = (TextView) findViewById(R.id.textview_rate);
        rateTxTView.setText(movieObject.getRate() + "/10");

        TextView descriptionTxTView = (TextView) findViewById(R.id.textview_description);
        descriptionTxTView.setText(movieObject.getDescription().toString());

        if (favoriteImage == null){
            favoriteImage = (ImageView) findViewById(R.id.favoriteImage);
        }
       if (controller.isFavorite()) {
            favoriteImage.setImageResource(R.drawable.toecu);
        } else {
            favoriteImage.setImageResource(R.drawable.hnd5a);
        }
    }
}

