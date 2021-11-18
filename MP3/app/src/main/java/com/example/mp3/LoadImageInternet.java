package com.example.mp3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageInternet extends AsyncTask<String, Void, Bitmap> {
    Bitmap bitmapHinh = null;
    ImageView imgHinh = null;
    public LoadImageInternet(ImageView imgHinh){
        this.imgHinh = imgHinh;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        try
        {
            URL url = new URL(strings[0]);
            InputStream inputStream = url.openConnection().getInputStream();
            bitmapHinh = BitmapFactory.decodeStream(inputStream);
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmapHinh;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imgHinh.setImageBitmap(bitmap);
    }
}
