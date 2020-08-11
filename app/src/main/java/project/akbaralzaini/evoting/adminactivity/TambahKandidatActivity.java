package project.akbaralzaini.evoting.adminactivity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiInterface;
import project.akbaralzaini.evoting.model.Kandidat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO : Restfull untuk create belum di buat.
public class TambahKandidatActivity extends Activity {

    EditText mNama,mKelas, mTanggalLahir, mVisi, mMisi, mPengalaman, mNis;
    Button btnSimpan;
    TextView btnBack;
    ApiInterface mApiInterface;
    String file_path="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kandidat);

        mNama = findViewById(R.id.nama_kandidat);
        mKelas = findViewById(R.id.kelas_kandidat);
        mTanggalLahir = findViewById(R.id.ttl_kandidat);
        mPengalaman = findViewById(R.id.pengalaman_kandidat);
        mVisi = findViewById(R.id.visi_kandidat);
        mMisi = findViewById(R.id.misi_kandidat);
        mNis = findViewById(R.id.nisn_kandidat);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnSimpan = findViewById(R.id.button_simpan);
        btnBack = findViewById(R.id.button_batal);

        btnSimpan.setOnClickListener(view -> {

            //TODO : BENERI UPLOAD IMAGE
            Call<Kandidat> postUpdateDelKandidatCall = mApiInterface.postKandidat(k);
            postUpdateDelKandidatCall.enqueue(new Callback<Kandidat>() {
                @Override
                public void onResponse(Call<Kandidat> call, Response<Kandidat> response) {
                    Kandidat kandidat = response.body();
                    //Log.e("S",kandidat.getNama());
                    AlertDialog.Builder builder = new AlertDialog.Builder(TambahKandidatActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Data Berhasil ditambahkan");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DashboardActivity.ma.refresh();
                            finish();
                        }
                    });
                    builder.show();
                }

                @Override
                public void onFailure(Call<Kandidat> call, Throwable t) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TambahKandidatActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Data Gagal ditambahkan");
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.e("Gagal","Gagal Menambahkan");
                        }
                    });
                    builder.show();
                    //Toast.makeText(TambahKandidatActivity.this, "Error",Toast.LENGTH_LONG).show();
                }
            });
        });

        btnBack.setOnClickListener(view -> {
            DashboardActivity.ma.refresh();
            finish();
        });



    }
}
