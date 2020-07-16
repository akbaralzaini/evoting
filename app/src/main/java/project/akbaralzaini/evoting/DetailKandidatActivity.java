package project.akbaralzaini.evoting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

                Intent nextScreen = new Intent(getApplicationContext(), EditKandidatActivity.class);
                startActivity(nextScreen);

            }
        });
    }
}
