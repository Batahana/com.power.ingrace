package com.example.compoweringrace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    // Duration of splash screen in milliseconds
    private static final int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay the execution of code inside the Runnable by the splash screen timeout
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start your main activity after the splash screen timeout
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);

                // Finish the current activity
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}