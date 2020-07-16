package project.akbaralzaini.evoting;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiInterface;
import project.akbaralzaini.evoting.adapter.KandidatAdapter;
import project.akbaralzaini.evoting.model.Kandidat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends Activity implements View.OnClickListener {

    RelativeLayout ButtonTambah;
    TextView ButtonProfil;
    TextView namaProfil;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static DashboardActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ButtonTambah = findViewById(R.id.button_tambahkandidat);
        ButtonProfil = findViewById(R.id.edit_button);
        namaProfil = findViewById(R.id.nama_profil);

        namaProfil.setText("Akbar Alzaini");
        ButtonTambah.setOnClickListener(this);
        ButtonProfil.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.list_kandidat);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;
        refresh();

    }

    private void refresh() {
        Call<List<Kandidat>> kandidatCall = mApiInterface.getKandidat();
        kandidatCall.enqueue(new Callback<List<Kandidat>>() {
            @Override
            public void onResponse(Call<List<Kandidat>> call, Response<List<Kandidat>> response) {
                List<Kandidat> KandidatList = response.body();
                Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        KandidatList.size());
                mAdapter = new KandidatAdapter(KandidatList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Kandidat>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
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
