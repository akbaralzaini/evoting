package project.akbaralzaini.evoting.adminactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiVotingInterface;
import project.akbaralzaini.evoting.adapter.HasilAdapter;
import project.akbaralzaini.evoting.model.HasilVoting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilActivity extends Activity {

    private PieChart mPieChart;
    ApiVotingInterface mApiInterface;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        mPieChart = findViewById(R.id.chart);


        mRecyclerView = findViewById(R.id.list_hasil1);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface = ApiClient.getClient().create(ApiVotingInterface.class);
        Call<List<HasilVoting>> hasilVotingCall = mApiInterface.getHasilVoting();
        hasilVotingCall.enqueue(new Callback<List<HasilVoting>>() {
            @Override
            public void onResponse(Call<List<HasilVoting>> call, Response<List<HasilVoting>> response) {
                List<HasilVoting> hasilVotingList = response.body();
                ArrayList<PieEntry> entries = new ArrayList<>();
                for (int i=0;i<hasilVotingList.size();i++){
                    entries.add(new PieEntry(Integer.parseInt(hasilVotingList.get(i).getJumlah()),hasilVotingList.get(i).getNama_ketua()));
                }
                PieDataSet pieDataSet = new PieDataSet(entries,"Nama Ketua");
                pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                PieData pieData = new PieData(pieDataSet);
                pieData.setValueTextSize(15f);
                mPieChart.setData(pieData);
                mPieChart.animateXY(3000, 3000);
                mPieChart.invalidate();


                mAdapter = new HasilAdapter(hasilVotingList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<HasilVoting>> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HasilActivity.this);
                builder.setTitle("Informasi");
                builder.setMessage("Data Gagal ditampilkan, Cek koneksi anda");
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.show();
            }
        });
    }
}
