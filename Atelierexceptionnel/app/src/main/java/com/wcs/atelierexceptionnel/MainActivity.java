package com.wcs.atelierexceptionnel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            maMethode("file://toto.test");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MainActivity", e.getMessage());
        } finally {
            System.out.print("caca");
        }


    }

    private int maMethode(String path) throws IOException {
        FileInputStream f = null;
        f = new FileInputStream(path);
        f.read();
        return 0;
    }
}
