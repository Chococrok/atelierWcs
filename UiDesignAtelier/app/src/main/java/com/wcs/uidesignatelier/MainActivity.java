package com.wcs.uidesignatelier;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Button mButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        mButton = (Button) findViewById(R.id.button);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        int orientation = super.getResources().getConfiguration().orientation;
        String toastMsg = "undef";
        switch (orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                toastMsg = "PORTRAIT";
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                toastMsg = "LANDSCAPE";
                break;
        }

        Toast.makeText(this, "onCreate() : " + toastMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;
        String toastMsg = "undef";
        switch (orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                toastMsg = "PORTRAIT";
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                toastMsg = "LANDSCAPE";
                break;
        }
        Toast.makeText(this, "From the manifest" + toastMsg, Toast.LENGTH_SHORT).show();
    }
}
