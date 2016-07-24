package sd_dtu.synergygoadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }
    public void onClickEditfinal(View view){

        Toast.makeText(getApplicationContext(), "Edited The Task", Toast.LENGTH_SHORT).show();
        Intent intent4=new Intent(EditActivity.this,MenuActivity.class);
        startActivity(intent4);
    }
}
