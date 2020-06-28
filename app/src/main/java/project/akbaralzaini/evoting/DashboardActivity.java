package project.akbaralzaini.evoting;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.akbaralzaini.evoting.adapter.KandidatAdapter;
import project.akbaralzaini.evoting.model.Kandidat;

public class DashboardActivity extends Activity implements View.OnClickListener {

    RelativeLayout ButtonTambah;
    TextView ButtonProfil;
    TextView namaProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ListView listView=(ListView) findViewById(R.id.list_kandidat);
        List<Kandidat> listKandidat=new ArrayList<Kandidat>();

        Kandidat kandidat1,kandidat2,kandidat3;

        kandidat1 = new Kandidat();
        kandidat1.setFoto(R.drawable.akbaralzaini);
        kandidat1.setNama("akbar alzaini");
        kandidat1.setKelas("Mipa 6");
        kandidat1.setNomor("Kandidat no 1");
        kandidat1.setVisi("bytsyu sstts6ts s6sbts6  s6bt6st 6v5s6 sv5s6b ty9 s7 s sy9sy9sy9sys ");

        listKandidat.add(kandidat1);

        kandidat2 = new Kandidat();
        kandidat2.setFoto(R.drawable.akbaralzaini);
        kandidat2.setNama("akbar alzaini");
        kandidat2.setKelas("Mipa 6");
        kandidat2.setNomor("Kandidat no 1");
        kandidat2.setVisi("bytsyu sstts6ts s6sbts6  s6bt6st 6v5s6 sv5s6b ty9 s7 s sy9sy9sy9sys ");

        listKandidat.add(kandidat2);

        kandidat3 = new Kandidat();
        kandidat3.setFoto(R.drawable.akbaralzaini);
        kandidat3.setNama("akbar alzaini");
        kandidat3.setKelas("Mipa 6");
        kandidat3.setNomor("Kandidat no 3");
        kandidat3.setVisi("bytsyu sstts6ts s6sbts6  s6bt6st 6v5s6 sv5s6b ty9 s7 s sy9sy9sy9sys ");

        listKandidat.add(kandidat3);

        listView.setAdapter(new KandidatAdapter(this,R.layout.list_item,listKandidat));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(DashboardActivity.this, DetailKandidatActivity.class);
                startActivity(i);
            }
        });

        ButtonTambah = findViewById(R.id.button_tambahkandidat);
        ButtonProfil = findViewById(R.id.edit_button);
        namaProfil = findViewById(R.id.nama_profil);

        namaProfil.setText("Akbar Alzaini");
        ButtonTambah.setOnClickListener(this);
        ButtonProfil.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_tambahkandidat:
                Intent i = new Intent(DashboardActivity.this, TambahKandidatActivity.class);
                startActivity(i);
                break;
            case R.id.edit_button:
                Intent b = new Intent(DashboardActivity.this, ProfilActivity.class);
                startActivity(b);
                break;
        }
    }
}
