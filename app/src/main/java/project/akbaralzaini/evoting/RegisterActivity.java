package project.akbaralzaini.evoting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.Date;

import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiUserInterface;
import project.akbaralzaini.evoting.model.PostUpdateDelUser;
import project.akbaralzaini.evoting.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity implements View.OnClickListener {

    EditText edtUsername, edtNama, edtPassword, edtTanggal_lahir, edtKelas, edtNis;
    ApiUserInterface mApiInterface;
    RelativeLayout btnRegister;
    Date currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.username_user);
        edtPassword = findViewById(R.id.password_user);
        edtNama = findViewById(R.id.nama_user);

        mApiInterface = ApiClient.getClient().create(ApiUserInterface.class);

        currentTime = Calendar.getInstance().getTime();


        btnRegister = findViewById(R.id.button_signup);
        btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_signup:
                User u = new User();
                u.setUsername(edtUsername.getText().toString());
                u.setNama(edtNama.getText().toString());
                u.setPassword(edtPassword.getText().toString());
                u.setRole("siswa");
                Call<User> postUpdateDelUserCall = mApiInterface.postUser(u);
                postUpdateDelUserCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setTitle("Informasi");
                        builder.setMessage("Data Berhasil ditambahkan");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent nextScreen = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(nextScreen);
                                finish();
                            }
                        });
                        builder.show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setTitle("Informasi");
                        builder.setMessage("Data Gagal ditambahkan");
                        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.e("Gagal","Gagal Menambahkan");
                            }
                        });
                        builder.show();
                        //Toast.makeText(TambahUserActivity.this, "Error",Toast.LENGTH_LONG).show();
                    }
                });
        }
    }
}
