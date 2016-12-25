package sd_dtu.synergygoadmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreviousAllotDetails extends AppCompatActivity {

    //ArrayAdapter<String> adapter;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
   //String[] agents={"agent1","agent2","agent3","agent4","agent5"};
    DatabaseReference mDatabasechecked;
   // ArrayList<String> agentid = new ArrayList<String>();

    ArrayList<RecyclerData> arrayList = new ArrayList<RecyclerData>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_allot_details);
        //final ListView agentlv=(ListView)findViewById(R.id.agentlv);

        if(isNetworkAvailable(getApplicationContext())) {
            getSupportActionBar().setTitle("Select an Agent");
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Wait...");
            progressDialog.show();
            progressDialog.setCancelable(true);


            mDatabasechecked = FirebaseDatabase.getInstance().getReference();

            mDatabasechecked.child("file").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot file : dataSnapshot.getChildren()) {
                        RecyclerData recyclerData = new RecyclerData(file.getKey());
                        arrayList.add(recyclerData);
                    }

                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    //adapter = new ArrayAdapter<String>(PreviousAllotDetails.this,R.layout.lv_custom_layout,R.id.list_item,agentid);
                    adapter = new RecyclerAdapter(arrayList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Unable to connect to the server", Toast.LENGTH_LONG).show();
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

//        agentlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent=new Intent(PreviousAllotDetails.this,DetailsAct.class);
//                startActivity(intent);
//            }
//        });
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
