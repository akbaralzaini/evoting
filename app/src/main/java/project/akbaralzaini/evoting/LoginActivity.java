package project.akbaralzaini.evoting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiUserInterface;
import project.akbaralzaini.evoting.adminactivity.DashboardActivity;
import project.akbaralzaini.evoting.model.Login;
import project.akbaralzaini.evoting.model.User;
import project.akbaralzaini.evoting.siswaActivity.DashboardSiswaActivity;
import project.akbaralzaini.evoting.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity implements View.OnClickListener {

    RelativeLayout buttonLogin;
    TextView btnRegister;
    EditText edtUsername, edtPassword;
    ApiUserInterface mApiInterface;
    ProgressDialog progress;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mApiInterface = ApiClient.getClient().create(ApiUserInterface.class);
        sharedPrefManager = new SharedPrefManager(this);

        edtUsername = findViewById(R.id.username_login);
        edtPassword = findViewById(R.id.password_login);
        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(this);

        btnRegister = findViewById(R.id.button_daftar);
        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
//                Intent b = new Intent(LoginActivity.this, DashboardActivity.class);
//                startActivity(b);
               // loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
                progress = new ProgressDialog(this);
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.show();

                requestLogin();
                break;
            case R.id.button_daftar:
                Intent c = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(c);
                break;
        }
    }

    private void requestLogin() {
        Login u = new Login();
        u.setUsername(edtUsername.getText().toString());
        u.setPassword(edtPassword.getText().toString());

        Call<User> Login = mApiInterface.loginRequest(u);
        Login.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User a = response.body();
                //Log.e("a",a.getNama().toString());
                if (a != null){
                    if (a.getRole().equals("siswa")){
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, a.getNama());
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, a.getId());
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_ROLE,a.getRole());
                        // Shared Pref ini berfungsi untuk menjadi trigger session login
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                        progress.dismiss();
                        Intent b = new Intent(LoginActivity.this, DashboardSiswaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(b);
                        finish();
                    }
                    else if(a.getRole().equals("admin")){
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, a.getNama());
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, a.getId());
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_ROLE,a.getRole());
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_PASSWORD,a.getPassword());
                        // Shared Pref ini berfungsi untuk menjadi trigger session login
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                        progress.dismiss();
                        Intent b = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(b);
                        finish();
                    }
                    else{
                        progress.dismiss();
                        Intent b = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(b);
                    }
                }
                else{
                    progress.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Password atau email salah");
                    builder.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

}
