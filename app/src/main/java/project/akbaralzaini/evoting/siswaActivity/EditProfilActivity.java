package project.akbaralzaini.evoting.siswaActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiUserInterface;
import project.akbaralzaini.evoting.Rest.ApiVotingInterface;
import project.akbaralzaini.evoting.adminactivity.DashboardActivity;
import project.akbaralzaini.evoting.adminactivity.EditPasActivity;
import project.akbaralzaini.evoting.model.User;
import project.akbaralzaini.evoting.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilActivity extends Activity {
    EditText edtNama;
    Button bSimpan;
    String role,password,username,id;
    ApiUserInterface mApiInterface;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        sharedPrefManager = new SharedPrefManager(this);

        edtNama = findViewById(R.id.nama_profil);
        mApiInterface = ApiClient.getClient().create(ApiUserInterface.class);

        Intent a = getIntent();
        edtNama.setText(a.getStringExtra("nama"));
        role = a.getStringExtra("role");
        password = a.getStringExtra("password");
        username = a.getStringExtra("username");
        id = a.getStringExtra("id");
        Log.d("ababba",role+"x"+username+"y"+password);

        bSimpan = findViewById(R.id.simpan_profil);

        bSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User us = new User();
                us.setRole(role);
                us.setNama(edtNama.getText().toString());
                us.setUsername(username);
                us.setPassword(password);

                Call<User> userCall = mApiInterface.putUser(id,us);
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfilActivity.this);
                        builder.setTitle("Informasi");
                        builder.setMessage("Data Berhasil Disimpan");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, edtNama.getText().toString());
                                Intent det = new Intent(EditProfilActivity.this, DashboardSiswaActivity.class);
                                startActivity(det);
                                finish();
                            }
                        });
                        builder.show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfilActivity.this);
                        builder.setTitle("Informasi");
                        builder.setMessage("Data Gagal ditambahkan");
                        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.e("Gagal","Gagal Update");
                            }
                        });
                        builder.show();
                    }
                });
            }
        });

    }
}
