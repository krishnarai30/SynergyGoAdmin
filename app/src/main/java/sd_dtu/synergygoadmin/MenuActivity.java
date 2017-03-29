package sd_dtu.synergygoadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MenuActivity extends AppCompatActivity {



    ArrayList<String> arrayList = new ArrayList<String>();
    Button prevbutton,newbtn,regbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String PREFS_NAME = "MyPrefsFile";


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");

//            prevbutton.setEnabled(false);

            // first time task
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            editor.putInt("selection-start", 0);
            editor.apply();

//            prevbutton.setEnabled(true);

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).apply();
        }
        this.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/OpenSans-Regular.ttf",true);
        setContentView(R.layout.activity_menu);

        prevbutton = (Button) findViewById(R.id.prevalbtn);
        newbtn = (Button) findViewById(R.id.newalbtn);
        regbtn = (Button) findViewById(R.id.newagentbtn);

    }

    public void onClicknew(View view){
        Intent intent=new Intent(MenuActivity.this,NewAllotAct.class);
//        DatabaseReference mref;
//        mref = FirebaseDatabase.getInstance().getReference();
//        mref.child("AgentID").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot file : dataSnapshot.getChildren()) {
//                    arrayList.add(file.getKey());
//                    Log.d("id",file.getKey());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(),"Unable to contact the server",Toast.LENGTH_LONG).show();
//            }
//        });
//        intent.putExtra("LIST",arrayList);
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
