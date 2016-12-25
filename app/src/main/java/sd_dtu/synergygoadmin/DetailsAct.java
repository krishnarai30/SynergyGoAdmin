package sd_dtu.synergygoadmin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsAct extends AppCompatActivity {

    TextView fileno,name2,pcontact,scontact,address,landmark;
    String agent,uniid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(isNetworkAvailable(getApplicationContext())) {

            getSupportActionBar().setTitle("Check Details");
            fileno = (TextView) findViewById(R.id.textView15);
            name2 = (TextView) findViewById(R.id.textView19);
            pcontact = (TextView) findViewById(R.id.textView23);
            scontact = (TextView) findViewById(R.id.textView27);
            address = (TextView) findViewById(R.id.textView31);
            landmark = (TextView) findViewById(R.id.textView35);

            fileno.setText(getIntent().getStringExtra("fileno"));
            name2.setText(getIntent().getStringExtra("name"));
            pcontact.setText(getIntent().getStringExtra("pcontact"));
            scontact.setText(getIntent().getStringExtra("scontact"));
            address.setText(getIntent().getStringExtra("address"));
            landmark.setText(getIntent().getStringExtra("landmark"));

            uniid = getIntent().getStringExtra("uniid");
            agent = getIntent().getStringExtra("agent");
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


    public void onClickEdit(View view){

        if(isNetworkAvailable(getApplicationContext())) {
            Intent intent3 = new Intent(DetailsAct.this, EditActivity.class);
            intent3.putExtra("agent", agent);
            intent3.putExtra("unique", uniid);
            startActivity(intent3);
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
