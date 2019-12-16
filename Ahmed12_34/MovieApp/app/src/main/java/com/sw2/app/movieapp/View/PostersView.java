package com.sw2.app.movieapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sw2.app.movieapp.API.JsonParsing;
import com.sw2.app.movieapp.Adapters.PostersAdapter;
import com.sw2.app.movieapp.Controller.PostersController;
import com.sw2.app.movieapp.Model.Movie;
import com.sw2.app.movieapp.R;

import java.util.List;

/**
 * Created by mahmuod nasr on 11/22/2016.
 */

public class PostersView extends AppCompatActivity {
    PostersController controller ;
    GridView gridView;
    PostersAdapter postersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poster_grid_view);
controller = new PostersController(getApplicationContext());
        gridView = (GridView) findViewById(R.id.grid_view_movies);
       BackgroundThread backgroundThread = new BackgroundThread() {
            @Override
            protected void onPostExecute(String stringBuffer) {
              JsonParsing  jsonParsing = new JsonParsing();
               List<Movie> movieList = jsonParsing.movieParsing(stringBuffer);
                controller.setMovies(movieList);
                postersAdapter = new PostersAdapter(getApplicationContext(), R.layout.poster_item, movieList);
                gridView.setAdapter(postersAdapter);
            }
        };


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PostersView.this, DetialView.class);
                intent.putExtra("movieObject", controller.getMovie(position));
                startActivity(intent);
            }
        });




        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=dc803b5dbdfccad4ec7f127673c2821b&language=en-US";
      //  movieList = controller.getMovies(url);
       backgroundThread.execute(url);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.posters_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String url;
        switch (item.getItemId()) {
            case R.id.topRated:
                url = "https://api.themoviedb.org/3/movie/top_rated?api_key=dc803b5dbdfccad4ec7f127673c2821b&language=en-US";
                updatPosters(controller.getMovies(url));
                break;

            case R.id.popular:
                url = "https://api.themoviedb.org/3/movie/popular?api_key=dc803b5dbdfccad4ec7f127673c2821b&language=en-US";
                updatPosters(controller.getMovies(url));
                break;

            case R.id.favorite:
                updatPosters(controller.getFavoriteMovies());
                break;

        }
        return true;
    }

    public void updatPosters(List<Movie> movies) {

        postersAdapter.clear();
        postersAdapter.addAll(movies);
        postersAdapter.notifyDataSetChanged();
    }
}
