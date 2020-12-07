package project.akbaralzaini.evoting.adminactivity;


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

import project.akbaralzaini.evoting.LoginActivity;
import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiInterface;
import project.akbaralzaini.evoting.Rest.ApiPasanganInterface;
import project.akbaralzaini.evoting.adapter.KandidatAdapter;
import project.akbaralzaini.evoting.adapter.PasanganAdapter;
import project.akbaralzaini.evoting.model.Kandidat;
import project.akbaralzaini.evoting.model.Pasangan;
import project.akbaralzaini.evoting.model.PasanganLkp;
import project.akbaralzaini.evoting.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends Activity implements View.OnClickListener {

    TextView bperolehan_suara,btnLogout;
    RelativeLayout ButtonTambah, rButtonTambahPasangan;
    TextView ButtonProfil;
    TextView tInternetHilang;
    TextView namaProfil;
    ApiInterface mApiInterface;
    SharedPrefManager sharedPrefManager;
    ApiPasanganInterface mApiPasanganInterface;
    private RecyclerView mRecyclerView,mRecyclerViewPasangan;
    private RecyclerView.Adapter mAdapter,mPasanganAdapter;
    private RecyclerView.LayoutManager mLayoutManager,mLayoutManagerpasangan;
    public static DashboardActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ButtonTambah = findViewById(R.id.button_tambahkandidat);
        rButtonTambahPasangan = findViewById(R.id.button_tambahpasangan);
        namaProfil = findViewById(R.id.nama_profil);
        tInternetHilang = findViewById(R.id.internet_hilang);
        bperolehan_suara = findViewById(R.id.perolehan_suara);
        btnLogout = findViewById(R.id.logout);

        sharedPrefManager = new SharedPrefManager(this);
        namaProfil.setText(sharedPrefManager.getSPNama());

        ButtonTambah.setOnClickListener(this);
        rButtonTambahPasangan.setOnClickListener(this);
        bperolehan_suara.setOnClickListener(this);


        //pemanggilan Recycleview untuk list
        mRecyclerView = findViewById(R.id.list_kandidat);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        mRecyclerViewPasangan = findViewById(R.id.list_pasangan);
        mLayoutManagerpasangan = new LinearLayoutManager(this);
        mRecyclerViewPasangan.setLayoutManager(mLayoutManagerpasangan);
        mApiPasanganInterface =ApiClient.getClient().create(ApiPasanganInterface.class);
        ma=this;
        refresh();

        btnLogout.setOnClickListener(v -> {
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN,false);
            Intent login =  new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(login);
            finish();
        });
    }

    public void refresh() {
        Call<List<Kandidat>> kandidatCall = mApiInterface.getKandidat();
        kandidatCall.enqueue(new Callback<List<Kandidat>>() {
            @Override
            public void onResponse(Call<List<Kandidat>> call, Response<List<Kandidat>> response) {
                List<Kandidat> KandidatList = response.body();
                mAdapter = new KandidatAdapter(KandidatList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Kandidat>> call, Throwable t) {
                tInternetHilang.setVisibility(View.VISIBLE);
            }
        });

        Call<List<PasanganLkp>> pasanganCall = mApiPasanganInterface.getPasanganLengkap();
        pasanganCall.enqueue(new Callback<List<PasanganLkp>>() {
            @Override
            public void onResponse(Call<List<PasanganLkp>> call, Response<List<PasanganLkp>> response) {
                List<PasanganLkp> PasanganList = response.body();
                mPasanganAdapter = new PasanganAdapter(PasanganList,DashboardActivity.this);
                mRecyclerViewPasangan.setAdapter(mPasanganAdapter);
            }

            @Override
            public void onFailure(Call<List<PasanganLkp>> call, Throwable t) {
                tInternetHilang.setVisibility(View.VISIBLE);
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

            case R.id.button_tambahpasangan:
                Intent pasanganac = new Intent(DashboardActivity.this, tambahPasanganActivity.class);
                startActivity(pasanganac);
                break;

            case R.id.perolehan_suara :
                Intent hasil = new Intent(DashboardActivity.this, HasilActivity.class);
                startActivity(hasil);
                break;
        }
    }
}
