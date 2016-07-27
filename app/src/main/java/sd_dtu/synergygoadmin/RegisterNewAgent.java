package sd_dtu.synergygoadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegisterNewAgent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_agent);
    }
    public void onClickRegfinalAgent(View view){
        Toast.makeText(getApplicationContext(), "Registered New Agent", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(RegisterNewAgent.this,MenuActivity.class);
        startActivity(intent);
    }
}
