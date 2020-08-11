package project.akbaralzaini.evoting.siswaActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiInterface;
import project.akbaralzaini.evoting.Rest.ApiPasanganInterface;
import project.akbaralzaini.evoting.adapter.KandidatAdapter;
import project.akbaralzaini.evoting.adapter.PasanganAdapter;
import project.akbaralzaini.evoting.adminactivity.DashboardActivity;
import project.akbaralzaini.evoting.model.Kandidat;
import project.akbaralzaini.evoting.model.Pasangan;
import project.akbaralzaini.evoting.model.PasanganLkp;
import project.akbaralzaini.evoting.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardSiswaActivity extends Activity {
    TextView tInternetHilang;
    TextView namaProfil;
    ImageView iKetua,iWakil;
    SharedPrefManager sharedPrefManager;
    ApiPasanganInterface mApiPasanganInterface;
    private RecyclerView mRecyclerViewPasangan;
    private RecyclerView.Adapter mPasanganAdapter;
    private RecyclerView.LayoutManager mLayoutManagerpasangan;
    public static DashboardSiswaActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_siswa);

        namaProfil = findViewById(R.id.nama_profil);
        sharedPrefManager = new SharedPrefManager(this);
        namaProfil.setText(sharedPrefManager.getSPNama());

        //pemanggilan Recycleview untuk list
        mRecyclerViewPasangan = findViewById(R.id.list_pasangan);
        mLayoutManagerpasangan = new LinearLayoutManager(this);
        mRecyclerViewPasangan.setLayoutManager(mLayoutManagerpasangan);
        mApiPasanganInterface =ApiClient.getClient().create(ApiPasanganInterface.class);

        ma=this;
        refresh();
    }

    public void refresh() {
        Call<List<PasanganLkp>> pasanganCall = mApiPasanganInterface.getPasanganLengkap();
        pasanganCall.enqueue(new Callback<List<PasanganLkp>>() {
            @Override
            public void onResponse(Call<List<PasanganLkp>> call, Response<List<PasanganLkp>> response) {
                List<PasanganLkp> PasanganList = response.body();
                mPasanganAdapter = new PasanganAdapter(PasanganList,DashboardSiswaActivity.this);
                mRecyclerViewPasangan.setAdapter(mPasanganAdapter);
            }

            @Override
            public void onFailure(Call<List<PasanganLkp>> call, Throwable t) {
                tInternetHilang.setVisibility(View.VISIBLE);
            }
        });
    }
}
