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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiUserInterface;
import project.akbaralzaini.evoting.adminactivity.DashboardActivity;
import project.akbaralzaini.evoting.model.User;
import project.akbaralzaini.evoting.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity implements View.OnClickListener {

    RelativeLayout buttonLogin;
    TextView btnRegister;
    ProgressDialog loading;
    EditText edtUsername, edtPassword;
    ApiUserInterface mApiInterface;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.button_login);

        edtUsername = findViewById(R.id.username_login);
        edtPassword = findViewById(R.id.password_login);

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
        /*
        User u = new User("",edtUsername.getText().toString(),edtPassword.getText().toString(),"");
        Call<User> Login = mApiInterface.loginRequest(u);
        Login.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    try{
                        JSONObject jsonRESULTS = new JSONObject(String.valueOf(response));

                        if (jsonRESULTS.getString("msg").equals("berhasil masuk")){

                            String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);

                            // Shared Pref ini berfungsi untuk menjadi trigger session login
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                            Intent b = new Intent(LoginActivity.this, DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(b);

                            finish();
                        } else {
                            // Jika login gagal
                            String error_message = jsonRESULTS.getString("error_msg");
                            Toast.makeText(LoginActivity.this, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("msg","Masih Error bosh");
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
            }
        });
        */

        if (edtUsername.getText().toString().equals("Admin") && edtPassword.getText().toString().equals("admin123")){
            loading.dismiss();
           // sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, edtUsername.getText().toString());

            // Shared Pref ini berfungsi untuk menjadi trigger session login
//            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
            Intent b = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(b);
        }
        else{
            loading.dismiss();
            Toast.makeText(LoginActivity.this, "error gobok", Toast.LENGTH_SHORT).show();
        }
    }
}
