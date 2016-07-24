package sd_dtu.synergygoadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PreviousAllotDetails extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    String[] agents={"agent1","agent2","agent3","agent4","agent5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_allot_details);
        ListView agentlv=(ListView)findViewById(R.id.agentlv);

        adapter = new ArrayAdapter<String>(this,R.layout.lv_custom_layout,R.id.list_item,agents);
        agentlv.setAdapter(adapter);

        agentlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(PreviousAllotDetails.this,DetailsAct.class);
                startActivity(intent);
            }
        });
    }
}
