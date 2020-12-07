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

import okhttp3.ResponseBody;
import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiPasanganInterface;
import project.akbaralzaini.evoting.Rest.ApiVotingInterface;
import project.akbaralzaini.evoting.model.Pasangan;
import project.akbaralzaini.evoting.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPasanganAdminActivity extends Activity {
    TextView tNomorUrut,tNamaKetua, tKelasKetua,tNamaWakil,tKelasWakil, tVisi,tMisi, tPengalamanKetua, tPengalamanWakil;
    Button bEdit;
    TextView tHapus;
    ImageView iKetua,iWakil;
    String id;
    SharedPrefManager sharedPrefManager;
    ApiPasanganInterface mApiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasangan_admin);

        tNomorUrut = findViewById(R.id.nomor_urut);
        tNamaKetua = findViewById(R.id.nama_ketua);
        tKelasKetua = findViewById(R.id.kelas_ketua);
        tNamaWakil = findViewById(R.id.nama_wakil);
        tKelasWakil = findViewById(R.id.kelas_wakil);
        tVisi = findViewById(R.id.visi_kandidat);
        tMisi = findViewById(R.id.misi_kandidat);
        tPengalamanKetua = findViewById(R.id.pengalaman_ketua);
        tPengalamanWakil = findViewById(R.id.pengalaman_wakil);
        iKetua = findViewById(R.id.gambar_ketua);
        iWakil = findViewById(R.id.gambar_wakil);
        tHapus = findViewById(R.id.button_hapus);
        bEdit = findViewById(R.id.button_edit);

        mApiInterface = ApiClient.getClient().create(ApiPasanganInterface.class);
        sharedPrefManager = new SharedPrefManager(DetailPasanganAdminActivity.this);
        Intent mIntent = getIntent();
        id = mIntent.getStringExtra("id");
        tVisi.setText(mIntent.getStringExtra("visi"));
        tMisi.setText(mIntent.getStringExtra("misi"));
        tNomorUrut.setText("Nomor Urut "+mIntent.getStringExtra("no_urut"));
        tNamaKetua.setText(mIntent.getStringExtra("nama_ketua"));
        tPengalamanKetua.setText(mIntent.getStringExtra("pengalaman_ketua"));
        tPengalamanWakil.setText(mIntent.getStringExtra("pengalaman_wakil"));
        tNamaWakil.setText(mIntent.getStringExtra("nama_wakil"));
        tKelasKetua.setText(mIntent.getStringExtra("kelas_ketua"));
        tKelasWakil.setText(mIntent.getStringExtra("kelas_wakil"));

        Log.d("sss",mIntent.getStringExtra("foto_ketua"));

        Picasso.get()
                .load(mIntent.getStringExtra("foto_ketua"))
                .resize(500,500)
                .into(iKetua);


        Picasso.get()
                .load(mIntent.getStringExtra("foto_wakil"))
                .resize(500,500)
                .into(iWakil);

        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailPasanganAdminActivity.this, EditPasActivity.class);
                i.putExtra("id", id);
                i.putExtra("visi", mIntent.getStringExtra("visi"));
                i.putExtra("misi", mIntent.getStringExtra("misi"));
                i.putExtra("no_urut", mIntent.getStringExtra("no_urut"));
                i.putExtra("id_ketua",mIntent.getStringExtra("id_ketua"));
                i.putExtra("id_wakil",mIntent.getStringExtra("id_wakil"));
                i.putExtra("nama_ketua",mIntent.getStringExtra("nama_ketua"));
                i.putExtra("nama_wakil",mIntent.getStringExtra("nama_wakil"));

                startActivity(i);
            }
        });


        tHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Pasangan> pasanganCall = mApiInterface.deletePasangan(id);
                pasanganCall.enqueue(new Callback<Pasangan>() {
                    @Override
                    public void onResponse(Call<Pasangan> call, Response<Pasangan> response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailPasanganAdminActivity.this);
                        builder.setTitle("Informasi");
                        builder.setMessage("Pasangan Berhasil di hapus");
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
                    public void onFailure(Call<Pasangan> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailPasanganAdminActivity.this);
                        builder.setTitle("Informasi");
                        builder.setMessage("Pasangan Gagal Dihapus, Cek koneksi anda");
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
            }
        });

        ImageView back = findViewById(R.id.backicon);
        back.setOnClickListener(v -> finish());
    }
}
