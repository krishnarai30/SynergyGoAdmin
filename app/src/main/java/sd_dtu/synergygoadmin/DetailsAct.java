package sd_dtu.synergygoadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsAct extends AppCompatActivity {

    TextView fileno,name2,pcontact,scontact,address,landmark;
    String agent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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

        agent = getIntent().getStringExtra("agent");
    }


    public void onClickEdit(View view){

        Intent intent3=new Intent(DetailsAct.this,EditActivity.class);
        intent3.putExtra("agent",agent);
        startActivity(intent3);
    }
}
