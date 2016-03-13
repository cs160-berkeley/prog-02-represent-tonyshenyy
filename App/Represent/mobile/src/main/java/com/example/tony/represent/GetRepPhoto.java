package com.example.tony.represent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by yshen on 3/12/2016.
 */
public class GetRepPhoto extends AsyncTask<String, Void, Bitmap> {
    ImageView repPhoto;

    public GetRepPhoto(ImageView repPhoto) {
        this.repPhoto = repPhoto;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap photo = null;
        try {
            InputStream inputStream = new java.net.URL(url).openStream();
            photo = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return photo;
    }

    protected void onPostExecute(Bitmap result) {
        repPhoto.setImageBitmap(result);
    }
}
