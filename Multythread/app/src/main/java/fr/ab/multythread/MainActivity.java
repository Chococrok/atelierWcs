package fr.ab.multythread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap b = loadImageFromNetwork(url);

                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(b);
                    }
                });

            }
        });
        thread.start();
        //new ImageDownloadTask(imageView).execute(url);
    }

    private static final String url = "https://s-media-cache-ak0.pinimg.com/736x/eb/00/c5/eb00c55fef4b63c2e1542ace9e4faab5.jpg";

    private Bitmap loadImageFromNetwork(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
        return myBitmap;
        } catch (IOException e) { return null; }
    }

    /*private class ImageDownloadTask extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... params) {

            return loadImageFromNetwork(url);
        }
        public ImageDownloadTask(ImageView iv){
            imageView = iv;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }*/
}
