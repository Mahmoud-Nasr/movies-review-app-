package com.sw2.app.movieapp.View;

import android.os.AsyncTask;

import com.sw2.app.movieapp.Model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by mahmuod nasr on 11/22/2016.
 */

public class BackgroundThread extends AsyncTask<String, Void, String> {

    /*    Context contextBackground;
    BaseAdapter baseAdapterBackground;
    GridView gridViewBackground;

    public Background(Context context, BaseAdapter baseAdapter, GridView gridView) {
        this.contextBackground = context;
        this.baseAdapterBackground = baseAdapter;
        this.gridViewBackground = gridView;
    }
*/
    public BackgroundThread() {
        super();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urlAPI) {
        StringBuffer stringBuffer;
        try {
            URL url = new URL(urlAPI[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();// to put data from the connection , not for storing data just bring them to app
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));// not understand it well,, Stored now in Buffer
            stringBuffer = new StringBuffer();
            stringBuffer.append(bufferedReader.readLine());// not understand well

            return stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String stringBuffer) {

    }

}