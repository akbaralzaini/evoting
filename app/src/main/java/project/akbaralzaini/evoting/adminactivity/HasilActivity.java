package project.akbaralzaini.evoting.adminactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiVotingInterface;
import project.akbaralzaini.evoting.adapter.HasilAdapter;
import project.akbaralzaini.evoting.adapter.VotersAdapter;
import project.akbaralzaini.evoting.model.HasilVoting;
import project.akbaralzaini.evoting.model.Voters;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilActivity extends Activity {

    private PieChart mPieChart;
    ApiVotingInterface mApiInterface;
    private RecyclerView.Adapter mAdapter,mVotersAdapter;
    private RecyclerView mRecyclerView,mVoters;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView ucapan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        mPieChart = findViewById(R.id.chart);
        ucapan = findViewById(R.id.text_pemenang);


        mRecyclerView = findViewById(R.id.list_hasil1);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mVoters = findViewById(R.id.list_voters);

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

                HasilVoting tertinggi = hasilVotingList.get(0);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    tertinggi = Collections.max(hasilVotingList, Comparator.comparing(HasilVoting::getJumlah));
                }

                String u = "Dari hasil voting pemilihan ketua senat stmik prabumulih tahun 2021/2022 Nilai tertinggi diraih oleh no urut "+tertinggi.getNo_urut()+", dll atas nama ketua "+tertinggi.getNama_ketua()+" .Selamat atas terpilihnya kami ucapkan selamat bekerja";
                ucapan.setText(u);


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
//                builder.show();
            }
        });

        Call<List<Voters>> callVoters = mApiInterface.getVoters();
        callVoters.enqueue(new Callback<List<Voters>>() {
            @Override
            public void onResponse(Call<List<Voters>> call, Response<List<Voters>> response) {
                List<Voters> votersList = response.body();

                mVotersAdapter = new VotersAdapter(votersList);
                mVoters.setAdapter(mVotersAdapter);

            }

            @Override
            public void onFailure(Call<List<Voters>> call, Throwable t) {
                Log.d("mamam",t.toString());
            }
        });

    }
}
