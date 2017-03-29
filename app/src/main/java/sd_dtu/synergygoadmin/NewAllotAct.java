package sd_dtu.synergygoadmin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.IntegerRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import me.anwarshahriar.calligrapher.Calligrapher;

public class NewAllotAct extends AppCompatActivity {
    EditText fileet,applicantet,contactpet,contactset,addet,landmarket,agentidet;
    Spinner addtype,agentreg;
    int i,r;
    String x;
    int c;
    ArrayAdapter<CharSequence> addadap;
    ArrayList<String> list = new ArrayList<String>();
    String file,applicant,contactp,contacts,add,landmark,agentid,stringaddtype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        setContentView(R.layout.activity_new_allot);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/OpenSans-Regular.ttf",true);

        if(isNetworkAvailable(getApplicationContext())) {
            DatabaseReference mref1;
            mref1 = FirebaseDatabase.getInstance().getReference();
            mref1.child("AgentID").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot file : dataSnapshot.getChildren()) {
                        list.add(file.getKey());
                        Log.d("id", file.getKey());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Unable to contact the server", Toast.LENGTH_LONG).show();
                }
            });
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

//        list = (ArrayList<String>) getIntent().getSerializableExtra("LIST");
//        agentreg = (Spinner)findViewById(R.id.agentspin);

//        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
//        System.out.println(Arrays.toString(cs));

//        String[] cr = list.toArray(new String[list.size()]);
//
//        ArrayAdapter<CharSequence> dataadapter  = ArrayAdapter.createFromResource(this,cr,R.layout.support_simple_spinner_dropdown_item);
//        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        agentreg.setAdapter(dataadapter);

        addtype = (Spinner) findViewById(R.id.addtypspinner);
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
                stringaddtype = "RESIDENTIAL";
            }
        });
    }

    public void onClickallocate(View view){

        if(isNetworkAvailable(getApplicationContext())) {

            agentidet = (EditText) findViewById(R.id.Agentidet);
            fileet = (EditText) findViewById(R.id.efilenoet);
            applicantet = (EditText) findViewById(R.id.nameet);
            contactpet = (EditText) findViewById(R.id.econtactpet);
            contactset = (EditText) findViewById(R.id.econtactset);
            addet = (EditText) findViewById(R.id.eaddet);
            landmarket = (EditText) findViewById(R.id.elandmarket);

            agentid = agentidet.getText().toString().trim();
            file = fileet.getText().toString().trim();
            applicant = applicantet.getText().toString();
            contactp = contactpet.getText().toString().trim();
            contacts = contactset.getText().toString().trim();
            add = addet.getText().toString();
            landmark = landmarket.getText().toString().trim();

            DatabaseReference mref;
            mref = FirebaseDatabase.getInstance().getReference();

            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            int i = prefs.getInt("selection-start", -1);
            i++;
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            editor.putInt("selection-start", i);
            editor.apply();
            x = Integer.toString(i);

            if (TextUtils.isEmpty(agentid)) {
                Toast.makeText(this, "Please enter Agent ID", Toast.LENGTH_LONG).show();
                return;
            }
            else {
//                mref.child("AgentID").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for(DataSnapshot file : dataSnapshot.getChildren())
//                        {
//                            String s = file.getKey().toString();
//                            if((agentid.equals(s)))
//                            {
//                                Log.d("EQUAL","IN HERE");
//                                r=0;
//                                break;
////                                finish();
//                            } else {
//                                Log.d("NOT EQUAL","IN HERE");
//                                r=1;
//                            }
//                        }
//                        if(r == 1) {
//                            Toast.makeText(getApplicationContext(), "AGENT ID NOT REGISTERED", Toast.LENGTH_LONG).show();
////                            Intent intent = new Intent(NewAllotAct.this, RegisterNewAgent.class);
////                            startActivity(intent);
////                            finish();
//                           // finish();
//                        }
//                        return;
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Toast.makeText(getApplicationContext(),"Unable to contact the server",Toast.LENGTH_LONG).show();
//                    }
//                });

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

            if (TextUtils.isEmpty(landmark)) {
                Toast.makeText(this, "Please enter Landmark", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(stringaddtype)) {
                Toast.makeText(this, "Please enter Address Type", Toast.LENGTH_LONG).show();
                return;
            }

            DatabaseReference mData;
            mData = FirebaseDatabase.getInstance().getReference();
            mData.child("file").child(agentid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int k = 0;
                    for(DataSnapshot file : dataSnapshot.getChildren()) {
                        String s = file.getKey().toString();
                        int n = Integer.parseInt(s);
                        if(n>k) {
                            k = n;
                        }
                        k = n+1;
                    }
                    x = Integer.toString(k);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            Log.d("R",Integer.toString(r));

            for(String a:list) {
                if(a.equals(agentid)){

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

                    Toast.makeText(getApplicationContext(), "Allocated New Task", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewAllotAct.this, MenuActivity.class);
                    startActivity(intent);
                    c=0;
                    break;
                } else {
                    c=1;
                }

            }
            if(c==1)
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("INCORRECT AGENT ID").setMessage("The entered agent id is either not registered OR is incorrect \n " +
                        "PLEASE REGISTER OR ENTER THE CORRECT AGENT ID").show();
                Toast.makeText(getApplicationContext(),"Agent ID not registered",Toast.LENGTH_LONG).show();
                c=0;
            }
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
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
