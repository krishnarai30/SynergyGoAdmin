package sd_dtu.synergygoadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DetailsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }


    public void onClickEdit(View view){
        Intent intent3=new Intent(DetailsAct.this,EditActivity.class);
        startActivity(intent3);
    }
}
