package project.akbaralzaini.evoting.adminactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import project.akbaralzaini.evoting.R;

public class DetailKandidatActivity extends Activity {

    Button editButton;
    TextView  mNama,mKelas,mTanggalLahir,mNomorUrut,mVisi,mMisi,mPengalaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kandidat);
        mNama = findViewById(R.id.nama_kandidat);
        mKelas = findViewById(R.id.kelas_kandidat);
        mTanggalLahir = findViewById(R.id.tanggal_lahir_kandidat);
        mNomorUrut = findViewById(R.id.nomor_urut_kandidat);
        mVisi = findViewById(R.id.visi_kandidat);
        mMisi = findViewById(R.id.misi_kandidat);
        mPengalaman = findViewById(R.id.pengalaman_kandidat);

        Intent mIntent = getIntent();
        mNama.setText(mIntent.getStringExtra("nama"));
        mKelas.setText(mIntent.getStringExtra("kelas"));
        mTanggalLahir.setText(mIntent.getStringExtra("tanggal_lahir"));
        mNomorUrut.setText("Nomor Urut "+mIntent.getStringExtra("id"));
        mVisi.setText(mIntent.getStringExtra("visi"));
        mMisi.setText(mIntent.getStringExtra("misi"));
        mPengalaman.setText(mIntent.getStringExtra("pengalaman"));

        editButton = findViewById(R.id.button_edit);

        editButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), EditKandidatActivity.class);
                i.putExtra("id",mIntent.getStringExtra("id"));
                i.putExtra("nama",mIntent.getStringExtra("nama"));
                i.putExtra("kelas",mIntent.getStringExtra("kelas"));
                i.putExtra("nis",mIntent.getStringExtra("nis"));
                i.putExtra("visi",mIntent.getStringExtra("visi"));
                i.putExtra("misi",mIntent.getStringExtra("misi"));
                i.putExtra("tanggal_lahir",mIntent.getStringExtra("tanggal_lahir"));
                i.putExtra("pengalaman",mIntent.getStringExtra("pengalaman"));
                i.putExtra("foto",mIntent.getStringExtra("foto"));
                startActivity(i);

            }
        });
    }
}
