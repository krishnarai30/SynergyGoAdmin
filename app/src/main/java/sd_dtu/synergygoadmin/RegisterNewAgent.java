package sd_dtu.synergygoadmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterNewAgent extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference mref;
    EditText emailet,passet;
    String email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_agent);

    }
    public void onClickRegfinalAgent(View view){

        if(isNetworkAvailable(getApplicationContext())) {

            //firebaseAuth = FirebaseAuth.getInstance();
            emailet = (EditText) findViewById(R.id.agentemailidreget);
            passet = (EditText) findViewById(R.id.agentpassreget);

            email = emailet.getText().toString().trim();
            pass = passet.getText().toString().trim();


            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter Agent ID", Toast.LENGTH_LONG).show();
                return;
            }

            if (TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
                return;
            }

            mref = FirebaseDatabase.getInstance().getReference();
            mref.child("AgentID").child(email).setValue(email);
            mref.child("AgentID").child(email).child("password").setValue(pass);


            Toast.makeText(getApplicationContext(), "Registered New Agent", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterNewAgent.this, MenuActivity.class);
            startActivity(intent);
        }
        else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("No Internet Connection...")
                    .setMessage("Click Here to set Active connection")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(R.drawable.error)
                    .show();
        }

//        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//
//                }else{
//                    Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });


    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
