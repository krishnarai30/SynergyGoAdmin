package sd_dtu.synergygoadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClicknew(View view){
        Intent intent=new Intent(MenuActivity.this,NewAllotAct.class);
        startActivity(intent);
    }
    public void onClickprev(View view){
        Intent intent=new Intent(MenuActivity.this,PreviousAllotDetails.class);
        startActivity(intent);
    }
    public void onClickRegAgent(View view){
        Intent intent=new Intent(MenuActivity.this,RegisterNewAgent.class);
        startActivity(intent);
    }
}
