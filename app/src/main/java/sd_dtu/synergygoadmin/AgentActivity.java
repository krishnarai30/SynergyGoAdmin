package sd_dtu.synergygoadmin;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AgentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    DatabaseReference databaseReference;
    DatabaseReference dbref;
    ArrayList<String> si = new ArrayList<String>();
    ArrayList<CardData> list = new ArrayList<CardData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);

        //CharSequence[] charSequence = getIntent().getCharSequenceArrayExtra("chararray");
        final String agent = getIntent().getStringExtra("agent");
        getSupportActionBar().setTitle("Agent ID : " + agent);



//        String[] entries = new String[charSequence.length];
//        int i=0;
//        for(CharSequence ch : charSequence)
//        {
//            entries[i++] = ch.toString();
//        }

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Wait...");
        progressDialog.show();
        progressDialog.setCancelable(true);



        databaseReference = FirebaseDatabase.getInstance().getReference();

            databaseReference.child("file").child(agent).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int i=0;
                    for(DataSnapshot file : dataSnapshot.getChildren())
                    {
                        si.add(file.getKey());
                        Log.d("String",si.get(i));
                        String name = file.child("Applicant's name").getValue().toString();
                        String address = file.child("Address").getValue().toString();
                        String addtype = file.child("Address Type").getValue().toString();
                        String cprimary = file.child("Contact Primary").getValue().toString();
                        String sprimary = file.child("Contact Secondary").getValue().toString();
                        String fileno = file.child("File").getValue().toString();
                        String landmark = file.child("Landmark").getValue().toString();
                        Log.d("Name",name);
                        Log.d("Address",address);
                        Log.d("Addtype",addtype);
                        Log.d("Contact Primary",cprimary);
                        Log.d("Contact Secondary",sprimary);
                        Log.d("File",fileno);
                        Log.d("Landmark",landmark);

                        CardData cardData = new CardData(name,fileno,address,addtype,landmark,cprimary,sprimary,agent);
                        list.add(cardData);
                        i++;
                    }
                    recyclerView = (RecyclerView) findViewById(R.id.card_recycler);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    adapter = new RecyclerCardAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(adapter);
                    Log.d("Size",Integer.toString(si.size()));
                    Log.d("Here","IN here");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Canceled!", Toast.LENGTH_LONG).show();
                }
            });


        progressDialog.dismiss();
//        dbref = FirebaseDatabase.getInstance().getReference();
//
//        for(int i=0;i<si.size();i++) {
//            Log.d("Entered", "Now here");
//
//            dbref.child("file").child(agent).child(si.get(i)).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    for (DataSnapshot file : dataSnapshot.getChildren()) {
//
//                        String name = file.child("Applicant's name").getKey();
//                        String address = file.child("Address").getKey();
//                        String addtype = file.child("Address Type").getKey();
//                        String cprimary = file.child("Contact Primary").getKey();
//                        String sprimary = file.child("Contact Secondary").getKey();
//                        String fileno = file.child("File").getKey();
//                        String landmark = file.child("Landmark").getKey();
//                        Log.d("Name", name);
//                        CardData cardData = new CardData(name, fileno, address, addtype, landmark, cprimary, sprimary);
//                        list.add(cardData);
//                    }
//                    recyclerView = (RecyclerView) findViewById(R.id.card_recycler);
//                    layoutManager = new LinearLayoutManager(getApplicationContext());
//                    recyclerView.setLayoutManager(layoutManager);
//                    recyclerView.setHasFixedSize(true);
//                    adapter = new RecyclerCardAdapter(getApplicationContext(), list);
//                    recyclerView.setAdapter(adapter);
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                    Toast.makeText(getApplicationContext(), "Unable to contact the server", Toast.LENGTH_LONG).show();
//
//                }
//            });

    }
}
