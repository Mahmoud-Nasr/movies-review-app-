package com.sw2.app.movieapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sw2.app.movieapp.Model.Comment;
import com.sw2.app.movieapp.R;

import java.util.List;

/**
 * Created by boody 2 on 23/11/2016.
 */

public class CommentAdapter extends ArrayAdapter {
    private Context context;
    private List<Comment> commentList;

    public CommentAdapter(Context context, int resource, List<Comment> commentList) {
        super(context, resource, commentList);
        this.commentList = commentList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        TextView textView = (TextView) row.findViewById(R.id.trailer_name);
        textView.setText(commentList.get(position).getAuthor().toString());
        return row;
    }
}
