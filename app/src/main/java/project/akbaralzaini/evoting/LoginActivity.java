package project.akbaralzaini.evoting;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiUserInterface;
import project.akbaralzaini.evoting.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity implements View.OnClickListener {

    RelativeLayout buttonLogin;
    TextView btnRegister;
    ProgressDialog loading;
    EditText edtUsername, edtPassword;
    ApiUserInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(this);
        mApiInterface = ApiClient.getClient().create(ApiUserInterface.class);

        btnRegister = findViewById(R.id.button_daftar);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
//                Intent b = new Intent(LoginActivity.this, DashboardActivity.class);
//                startActivity(b);
                loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
                requestLogin();
                break;
            case R.id.button_daftar:
                Intent c = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(c);
                break;
        }
    }

    private void requestLogin() {
        User u = new User("",edtUsername.getText().toString(),edtPassword.getText().toString(),"");
        Call<User> Login = mApiInterface.loginRequest(u);
        Login.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    try{
                        
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
