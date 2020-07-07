package project.akbaralzaini.evoting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends Activity implements View.OnClickListener {

    RelativeLayout buttonLogin;
    TextView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                Intent b = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(b);
                break;
            case R.id.button_daftar:
                Intent c = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(c);
                break;
        }
    }
}
