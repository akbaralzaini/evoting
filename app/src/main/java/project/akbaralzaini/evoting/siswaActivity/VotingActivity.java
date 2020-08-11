package project.akbaralzaini.evoting.siswaActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.RegisterActivity;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiUserInterface;
import project.akbaralzaini.evoting.Rest.ApiVotingInterface;
import project.akbaralzaini.evoting.adminactivity.EditKandidatActivity;
import project.akbaralzaini.evoting.model.Voting;
import project.akbaralzaini.evoting.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VotingActivity extends Activity implements View.OnClickListener{
    TextView tNomorUrut,tNamaKetua, tKelasKetua,tNamaWakil,tKelasWakil, tVisi,tMisi, tPengalamanKetua, tPengalamanWakil;
    Button bVoting;
    ImageView iKetua,iWakil;
    String id;
    SharedPrefManager sharedPrefManager;
    ApiVotingInterface mApiInterface;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

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

        mApiInterface = ApiClient.getClient().create(ApiVotingInterface.class);
        sharedPrefManager = new SharedPrefManager(VotingActivity.this);
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

        //String url = "http://192.168.31.2/uploads/kandidat/"+mIntent.getStringExtra("id_ketua")+"/"+mIntent.getStringExtra("foto_ketua");
        Picasso.get().load(mIntent.getStringExtra("foto_ketua")).resize(500,500).into(iKetua);

        //String urlw = "http://192.168.31.2/uploads/kandidat/"+mIntent.getStringExtra("id_wakil")+"/"+mIntent.getStringExtra("foto_wakil");
        Picasso.get().load(mIntent.getStringExtra("foto_wakil")).resize(500,500).into(iWakil);

        bVoting = findViewById(R.id.button_votinga);
        bVoting.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_votinga:
                Voting v = new Voting();
                v.setId_pasangan(id);
                v.setId_user(sharedPrefManager.getSpId());
                v.setTanggal_voting(getDateTime());

                Log.e("Aa",v.getId_user());
                Call<Voting> votingCall = mApiInterface.Createvoting(v);
                votingCall.enqueue(new Callback<Voting>() {
                    @Override
                    public void onResponse(Call<Voting> call, Response<Voting> response) {
                        Voting vot = response.body();
                        if (vot == null){
                            AlertDialog.Builder builder = new AlertDialog.Builder(VotingActivity.this);
                            builder.setTitle("Informasi");
                            builder.setMessage("Ada Telah melakukan Voting");
                            builder.show();
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(VotingActivity.this);
                            builder.setTitle("Informasi");
                            builder.setMessage("Voting Berhasil");
                            builder.show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Voting> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(VotingActivity.this);
                        builder.setTitle("Informasi");
                        builder.setMessage("Voting Gagal");
                        builder.show();
                    }
                });
        }
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
