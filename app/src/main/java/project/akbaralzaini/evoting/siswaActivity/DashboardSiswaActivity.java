package project.akbaralzaini.evoting.siswaActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
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
import project.akbaralzaini.evoting.adminactivity.HasilActivity;
import project.akbaralzaini.evoting.model.Kandidat;
import project.akbaralzaini.evoting.model.Pasangan;
import project.akbaralzaini.evoting.model.PasanganLkp;
import project.akbaralzaini.evoting.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardSiswaActivity extends Activity {
    TextView bperolehan_suara;
    TextView tInternetHilang;
    TextView namaProfil;
    TextView tEditProfil;
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

        bperolehan_suara = findViewById(R.id.perolehan_suaras);
        namaProfil = findViewById(R.id.nama_profil);
        sharedPrefManager = new SharedPrefManager(this);
        namaProfil.setText(sharedPrefManager.getSPNama());
        tEditProfil = findViewById(R.id.update_profil);

        bperolehan_suara.setOnClickListener(view -> {
            Intent hasil = new Intent(DashboardSiswaActivity.this, HasilActivity.class);
            startActivity(hasil);
        });

        tEditProfil.setOnClickListener(view -> {
            Intent edit = new Intent(DashboardSiswaActivity.this,EditProfilActivity.class);
            edit.putExtra("nama",sharedPrefManager.getSPNama());
            edit.putExtra("username",sharedPrefManager.getSpUsername());
            edit.putExtra("password",sharedPrefManager.getSpPassword());
            edit.putExtra("role",sharedPrefManager.getRole());
            edit.putExtra("id",sharedPrefManager.getSpId());
            startActivity(edit);
        });

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
