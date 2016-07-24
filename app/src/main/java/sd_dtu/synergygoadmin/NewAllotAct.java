package sd_dtu.synergygoadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NewAllotAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_allot);
    }

    public void onClickallocate(View view){

        Toast.makeText(getApplicationContext(), "Allocated New Task", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(NewAllotAct.this,MenuActivity.class);
        startActivity(intent);
    }
}
