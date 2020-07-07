package project.akbaralzaini.evoting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class RegisterActivity extends Activity implements View.OnClickListener {

    RelativeLayout btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.button_signup);

        btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_signup:
                Intent signupac = new Intent(RegisterActivity.this,DashboardActivity.class);
                startActivity(signupac);
        }
    }
}
