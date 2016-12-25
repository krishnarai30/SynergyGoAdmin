package sd_dtu.synergygoadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {

    String agentid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        agentid = getIntent().getStringExtra("agent");

    }
    public void onClickEditfinal(View view){

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("file").child(agentid).child(x).child("Address Type").setValue(stringaddtype);
        mDatabase.child("file").child(agentid).child(x).child("Agent ID").setValue(agentid);
        mDatabase.child("file").child(agentid).child(x).child("Applicant's name").setValue(applicant);
        mDatabase.child("file").child(agentid).child(x).child("Contact Primary").setValue(contactp);
        mDatabase.child("file").child(agentid).child(x).child("Contact Secondary").setValue(contacts);
        mDatabase.child("file").child(agentid).child(x).child("Address").setValue(add);
        mDatabase.child("file").child(agentid).child(x).child("Landmark").setValue(landmark);
        mDatabase.child("file").child(agentid).child(x).child("File").setValue(file);

        Toast.makeText(getApplicationContext(), "Edited The Task", Toast.LENGTH_SHORT).show();
        Intent intent4=new Intent(EditActivity.this,MenuActivity.class);
        startActivity(intent4);
    }
}
