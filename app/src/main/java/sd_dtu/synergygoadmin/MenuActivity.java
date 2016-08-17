package sd_dtu.synergygoadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");

            // first time task
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            editor.putInt("selection-start", 0);
            editor.apply();

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).apply();
        }
        setContentView(R.layout.activity_menu);

    }

    public void onClicknew(View view){
        Intent intent=new Intent(MenuActivity.this,NewAllotAct.class);
        startActivity(intent);
    }
    public void onClickprev(View view){
        Intent intent=new Intent(MenuActivity.this,PreviousAllotDetails.class);
        startActivity(intent);
    }
    public void onClickRegAgent(View view){

        Intent intent=new Intent(MenuActivity.this,RegisterNewAgent.class);
        startActivity(intent);
    }
}
