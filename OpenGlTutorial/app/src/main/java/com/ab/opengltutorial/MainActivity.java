package com.ab.opengltutorial;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;
    private GLSurfaceView.Renderer mRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGLView = (GLSurfaceView) findViewById(R.id.GLSurfaceView);
        mGLView.setEGLContextClientVersion(2);
        mRenderer = new MyGLRenderer();
        mGLView.setRenderer(mRenderer);
        mGLView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        showDialog();




    }

    void showDialog() {

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        MyDialogFragment df = MyDialogFragment.newInstance();
        df.show(getSupportFragmentManager(), "int");
    }
}
