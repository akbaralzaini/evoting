package project.akbaralzaini.evoting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import project.akbaralzaini.evoting.Rest.ApiInterface;
import project.akbaralzaini.evoting.model.PostUpdateDelKandidat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditKandidatActivity extends Activity {
    EditText mNama,mKelas, mTanggalLahir, mVisi, mMisi, mPengalaman, mNis;
    Button btnSimpan;
    TextView btnBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kandidat);

        mNama = findViewById(R.id.nama_kandidat);
        mKelas = findViewById(R.id.kelas_kandidat);
        mTanggalLahir = findViewById(R.id.ttl_kandidat);
        mPengalaman = findViewById(R.id.pengalaman_kandidat);
        mVisi = findViewById(R.id.visi_kandidat);
        mMisi = findViewById(R.id.misi_kandidat);
        mNis = findViewById(R.id.nisn_kandidat);

        Intent mIntent = getIntent();
        mNama.setText(mIntent.getStringExtra("nama"));
        mKelas.setText(mIntent.getStringExtra("kelas"));
        mTanggalLahir.setText(mIntent.getStringExtra("tanggal_lahir"));
        mVisi.setText(mIntent.getStringExtra("visi"));
        mMisi.setText(mIntent.getStringExtra("misi"));
        mPengalaman.setText(mIntent.getStringExtra("pengalaman"));
        mNis.setText(mIntent.getStringExtra("nis"));
        //TODO : SET DATA UPDATE
        btnSimpan = findViewById(R.id.button_simpan);

        btnSimpan.setOnClickListener(view -> {
            //Log.i("TES",mIntent.getStringExtra("id"));
            Call<PostUpdateDelKandidat> postUpdateDelKandidatCall = mApiInterface.putKandidat(mIntent.getStringExtra("id"),mNama.getText().toString(),mKelas.getText().toString(),mNis.getText().toString(),mVisi.getText().toString(),mMisi.getText().toString(),mTanggalLahir.getText().toString(),mPengalaman.getText().toString(),1);
            postUpdateDelKandidatCall.enqueue(new Callback<PostUpdateDelKandidat>() {
                @Override
                public void onResponse(Call<PostUpdateDelKandidat> call, Response<PostUpdateDelKandidat> response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditKandidatActivity.this);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditKandidatActivity.this);
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

    }
}
