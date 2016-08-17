package sd_dtu.synergygoadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NewAllotAct extends AppCompatActivity {
    EditText fileet,applicantet,contactpet,contactset,addet,landmarket,agentidet;
    Spinner addtype;
    int i;
    String x;
    ArrayAdapter<CharSequence> addadap;
    String file,applicant,contactp,contacts,add,landmark,agentid,stringaddtype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_allot);
    }

    public void onClickallocate(View view){

        agentidet=(EditText)findViewById(R.id.Agentidet);
        fileet=(EditText)findViewById(R.id.efilenoet);
        applicantet=(EditText)findViewById(R.id.nameet);
        contactpet=(EditText)findViewById(R.id.econtactpet);
        contactset=(EditText)findViewById(R.id.econtactset);
        addet=(EditText)findViewById(R.id.eaddet);
        landmarket=(EditText)findViewById(R.id.elandmarket);
        addtype=(Spinner)findViewById(R.id.addtypspinner);

        addadap=ArrayAdapter.createFromResource(this,R.array.add_type,R.layout.support_simple_spinner_dropdown_item);
        addadap.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        addtype.setAdapter(addadap);
        addtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        stringaddtype="Office";
                        break;
                    case 1:
                        stringaddtype="Residence";
                        break;
                    case 2:
                        stringaddtype=" Business";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                stringaddtype="Residence";
            }
        });

        agentid=agentidet.getText().toString().trim();
        file=fileet.getText().toString().trim();
        applicant=applicantet.getText().toString();
        contactp=contactpet.getText().toString().trim();
        contacts=contactset.getText().toString().trim();
        add=addet.getText().toString();
        landmark=landmarket.getText().toString().trim();

        if(TextUtils.isEmpty(agentid)){
            Toast.makeText(this,"Please enter Agent ID",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(file)){
            Toast.makeText(this,"Please enter File No.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(applicant)){
            Toast.makeText(this,"Please enter Applicant Name",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(contactp)){
            Toast.makeText(this,"Please enter Primary Contact No.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(contacts)){
            Toast.makeText(this,"Please enter Secondary Contact",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(add)){
            Toast.makeText(this,"Please enter Address",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(landmark)){
            Toast.makeText(this,"Please enter Landmark",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(stringaddtype)){
            Toast.makeText(this,"Please enter Address Type",Toast.LENGTH_LONG).show();
            return;
        }
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        int i = prefs.getInt("selection-start",-1);
        i++;
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putInt("selection-start", i);
        editor.apply();
        x=Integer.toString(i);
        mDatabase.child("file").child(x).child("Address Type").setValue(stringaddtype);
        mDatabase.child("file").child(x).child("Agent ID").setValue(agentid);
        mDatabase.child("file").child(x).child("Applicant's name").setValue(applicant);
        mDatabase.child("file").child(x).child("Contact Primary").setValue(contactp);
        mDatabase.child("file").child(x).child("Contact Secondary").setValue(contacts);
        mDatabase.child("file").child(x).child("Address").setValue(add);
        mDatabase.child("file").child(x).child("Landmark").setValue(landmark);
        mDatabase.child("file").child(x).child("File").setValue(file);




        Toast.makeText(getApplicationContext(), "Allocated New Task", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(NewAllotAct.this,MenuActivity.class);
        startActivity(intent);
    }
}
