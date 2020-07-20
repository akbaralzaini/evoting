package project.akbaralzaini.evoting;

import androidx.appcompat.app.AppCompatActivity;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiInterface;
import project.akbaralzaini.evoting.model.PostUpdateDelKandidat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO : Restfull untuk create belum di buat.
public class TambahKandidatActivity extends Activity {

    EditText mNama,mKelas, mTanggalLahir, mVisi, mMisi, mPengalaman, mNisn;
    Button btnSimpan;
    TextView btnBack;
    ApiInterface mApiInterface;

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
        mNisn = findViewById(R.id.nisn_kandidat);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnSimpan = findViewById(R.id.button_simpan);
        btnBack = findViewById(R.id.button_batal);

        btnSimpan.setOnClickListener(view -> {
            Call<PostUpdateDelKandidat> postUpdateDelKandidatCall = mApiInterface.postKandidat(mNama.getText().toString(),mKelas.getText().toString(),mNisn.getText().toString(),mVisi.getText().toString(),mMisi.getText().toString(),mTanggalLahir.getText().toString(),mPengalaman.getText().toString(),1);
            postUpdateDelKandidatCall.enqueue(new Callback<PostUpdateDelKandidat>() {
                @Override
                public void onResponse(Call<PostUpdateDelKandidat> call, Response<PostUpdateDelKandidat> response) {
                    //Toast.makeText(getApplicationContext(), "Berhasil Ditambahkan",Toast.LENGTH_LONG).show();
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
                public void onFailure(Call<PostUpdateDelKandidat> call, Throwable t) {
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
