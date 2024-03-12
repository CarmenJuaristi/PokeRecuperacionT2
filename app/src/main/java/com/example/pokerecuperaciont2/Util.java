package com.example.pokerecuperaciont2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Util {
    // Descargaremos la imagen desde una Url
    public static void downloadBitmapToImageView(String url, ImageView imageView) {
        //Primero obtendremos el contexto de ImageView
        Context context = imageView.getContext();
        if (context instanceof Activity) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //Ahora obtenemos un Bitmap
                    Bitmap image = getBitmapFromURL(url);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        // Ahora establecemos el Bitmap que hemos descargado en el ImageView.
                        public void run() {
                            imageView.setImageBitmap(image);
                        }
                    });
                }
            });
            thread.start();
        }
    }


    private static Bitmap getBitmapFromURL(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream input = connection.getInputStream();

            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

