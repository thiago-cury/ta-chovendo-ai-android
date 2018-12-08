package thiagocury.eti.br.tachovendoaii.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import thiagocury.eti.br.tachovendoaii.R;

public class SplashScreen extends AppCompatActivity {

    //SPLASH SCREEN
    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent it = new Intent(SplashScreen.this, HomeActivity.class);
                startActivity(it);
                finish();

            }
        },SPLASH_SCREEN_TIME_OUT);
    }
}
