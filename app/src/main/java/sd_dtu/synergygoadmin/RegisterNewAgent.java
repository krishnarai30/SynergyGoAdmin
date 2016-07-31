package sd_dtu.synergygoadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class RegisterNewAgent extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText emailet,passet;
    String email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_agent);

    }
    public void onClickRegfinalAgent(View view){

        firebaseAuth =FirebaseAuth.getInstance();
        emailet=(EditText)findViewById(R.id.agentemailidreget);
        passet=(EditText)findViewById(R.id.agentpassreget);

        email=emailet.getText().toString().trim();
        pass=passet.getText().toString().trim();



        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }



        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registered New Agent", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterNewAgent.this,MenuActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Registeration failed", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
