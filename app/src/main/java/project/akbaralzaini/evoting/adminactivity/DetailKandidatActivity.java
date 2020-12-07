package project.akbaralzaini.evoting.adminactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiInterface;
import project.akbaralzaini.evoting.model.Kandidat;
import project.akbaralzaini.evoting.model.Pasangan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKandidatActivity extends Activity {

    Button editButton;
    TextView  mNama,mKelas,mTanggalLahir,mNomorUrut,mVisi,mMisi,mPengalaman,tHapus;
    ImageView iFotoKandidat;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kandidat);
        mNama = findViewById(R.id.nama_kandidat);
        mKelas = findViewById(R.id.kelas_kandidat);
        mNomorUrut = findViewById(R.id.nomor_urut_kandidat);
        mPengalaman = findViewById(R.id.pengalaman_kandidat);
        iFotoKandidat = findViewById(R.id.gambar_kandidat);
        tHapus = findViewById(R.id.button_hapus);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Intent mIntent = getIntent();
        mNama.setText(mIntent.getStringExtra("nama"));
        mKelas.setText(mIntent.getStringExtra("kelas"));
        mNomorUrut.setText(mIntent.getStringExtra("nis"));
        mPengalaman.setText(mIntent.getStringExtra("pengalaman"));
        String url = "https://voting.pkitapp.com/public/uploads/kandidat/"+mIntent.getStringExtra("id")+"/"+mIntent.getStringExtra("foto");
        Picasso.get().load(url).into(iFotoKandidat);

        editButton = findViewById(R.id.button_edit);

        editButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), EditKandidatActivity.class);
                i.putExtra("id",mIntent.getStringExtra("id"));
                i.putExtra("nama",mIntent.getStringExtra("nama"));
                i.putExtra("kelas",mIntent.getStringExtra("kelas"));
                i.putExtra("nis",mIntent.getStringExtra("nis"));
                i.putExtra("pengalaman",mIntent.getStringExtra("pengalaman"));
                i.putExtra("foto",mIntent.getStringExtra("foto"));
                startActivity(i);

            }
        });

        tHapus.setOnClickListener(view -> {
            Call<Kandidat> kandidatCall = mApiInterface.deleteKandidat(mIntent.getStringExtra("id"));
            kandidatCall.enqueue(new Callback<Kandidat>() {
                @Override
                public void onResponse(Call<Kandidat> call, Response<Kandidat> response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailKandidatActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Kandidat Berhasil di hapus");
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailKandidatActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Kandidat Gagal Dihapus, Cek koneksi anda");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DashboardActivity.ma.refresh();
                            finish();
                        }
                    });
                    builder.show();
                }
            });

        });


        ImageView back = findViewById(R.id.backicon);
        back.setOnClickListener(v -> finish());

    }
}
