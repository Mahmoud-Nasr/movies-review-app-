package com.sw2.app.movieapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.sw2.app.movieapp.Model.Movie;
import com.sw2.app.movieapp.R;

import java.util.List;

/**
 * Created by mahmuod nasr on 11/22/2016.
 */

public class PostersAdapter extends ArrayAdapter {
    private List<Movie> movieList;
    private Context context;

    public static class RecycleView{
        ImageView posterImage;
        Movie movie;
    }

    public PostersAdapter(Context context, int resource, List<Movie> movieList) {
        super(context, resource, movieList);
        this.movieList = movieList;
        this.context = context;

    }

    //private Context mContext;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row = LayoutInflater.from(context).inflate(R.layout.poster_item, parent, false);
            RecycleView Holder = new RecycleView();
            Holder.posterImage = (ImageView) row.findViewById(R.id.imageView_item);
            Holder.movie = movieList.get(position);
            row.setTag(Holder);
        }
        RecycleView Holder = (RecycleView) row.getTag();
        Holder.posterImage = (ImageView) row.findViewById(R.id.imageView_item);
        Holder.movie = movieList.get(position);

        //Movie movieObject = movieList.get(position);
        //imageView.setImageResource(movieObject.getImage());
        Picasso.with(context).load(Holder.movie.getImage()).into(Holder.posterImage);
        return row;
    }

}
