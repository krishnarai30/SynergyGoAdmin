package sd_dtu.synergygoadmin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {

    String agentid,uniqueid,stringaddtype,landm,file,applicant,add,contactp,contacts;
    EditText name,address,pcontact,scontact,landmark,agent,fileno;
    ArrayAdapter<CharSequence> addadap;
    Spinner addtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        agentid = getIntent().getStringExtra("agent");
        uniqueid = getIntent().getStringExtra("unique");

        Log.d("UNIQUE",uniqueid);

        name = (EditText) findViewById(R.id.name5);
        address = (EditText) findViewById(R.id.eadd);
        pcontact = (EditText) findViewById(R.id.econtactp);
        scontact = (EditText) findViewById(R.id.econtacts);
        landmark = (EditText) findViewById(R.id.elandmark);
        agent = (EditText) findViewById(R.id.Agentidr);
        fileno = (EditText)findViewById(R.id.efileno);
        addtype = (Spinner) findViewById(R.id.addtypspin);


        name.setText(getIntent().getStringExtra("name"));
        address.setText(getIntent().getStringExtra("address"));
        pcontact.setText(getIntent().getStringExtra("pcontact"));
        scontact.setText(getIntent().getStringExtra("scontact"));
        landmark.setText(getIntent().getStringExtra("landmark"));
        fileno.setText(getIntent().getStringExtra("fileno"));



    }
    public void onClickEditfinal(View view) {


        if (isNetworkAvailable(getApplicationContext())) {
            addadap = ArrayAdapter.createFromResource(this, R.array.add_type, R.layout.support_simple_spinner_dropdown_item);
            addadap.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            addtype.setAdapter(addadap);
            addtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i) {
                        case 0:
                            stringaddtype = "OFFICE";
                            break;
                        case 1:
                            stringaddtype = "RESIDENTIAL";
                            break;
                        case 2:
                            stringaddtype = " BUSINESS";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    stringaddtype = "Residence";
                }
            });

            agentid = agent.getText().toString().trim();
            file = fileno.getText().toString().trim();
            applicant = name.getText().toString();
            contactp = pcontact.getText().toString().trim();
            contacts = scontact.getText().toString().trim();
            add = address.getText().toString();
            landm = landmark.getText().toString().trim();

            if (TextUtils.isEmpty(agentid)) {
                Toast.makeText(this, "Please enter Agent ID", Toast.LENGTH_LONG).show();
                return;
            }

            if (TextUtils.isEmpty(file)) {
                Toast.makeText(this, "Please enter File No.", Toast.LENGTH_LONG).show();
                return;
            }

            if (TextUtils.isEmpty(applicant)) {
                Toast.makeText(this, "Please enter Applicant Name", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(contactp)) {
                Toast.makeText(this, "Please enter Primary Contact No.", Toast.LENGTH_LONG).show();
                return;
            }

            if (TextUtils.isEmpty(contacts)) {
                Toast.makeText(this, "Please enter Secondary Contact", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(add)) {
                Toast.makeText(this, "Please enter Address", Toast.LENGTH_LONG).show();
                return;
            }

            if (TextUtils.isEmpty(landm)) {
                Toast.makeText(this, "Please enter Landmark", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(stringaddtype)) {
                Toast.makeText(this, "Please enter Address Type", Toast.LENGTH_LONG).show();
                return;
            }


            DatabaseReference mDatabase;
            mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.child("file").child(agentid).child(uniqueid).child("Address Type").setValue(stringaddtype);
            mDatabase.child("file").child(agentid).child(uniqueid).child("Agent ID").setValue(agentid);
            mDatabase.child("file").child(agentid).child(uniqueid).child("Applicant's name").setValue(applicant);
            mDatabase.child("file").child(agentid).child(uniqueid).child("Contact Primary").setValue(contactp);
            mDatabase.child("file").child(agentid).child(uniqueid).child("Contact Secondary").setValue(contacts);
            mDatabase.child("file").child(agentid).child(uniqueid).child("Address").setValue(add);
            mDatabase.child("file").child(agentid).child(uniqueid).child("Landmark").setValue(landm);
            mDatabase.child("file").child(agentid).child(uniqueid).child("File").setValue(file);

            Toast.makeText(getApplicationContext(), "Edited The Task", Toast.LENGTH_SHORT).show();
            Intent intent4 = new Intent(EditActivity.this, MenuActivity.class);
            startActivity(intent4);
        } else {
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
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
