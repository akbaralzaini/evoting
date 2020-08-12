package project.akbaralzaini.evoting.adminactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiInterface;
import project.akbaralzaini.evoting.Rest.ApiVotingInterface;
import project.akbaralzaini.evoting.adapter.HasilAdapter;
import project.akbaralzaini.evoting.model.HasilVoting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilActivity extends Activity {

    ApiVotingInterface mApiInterface;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        mRecyclerView = findViewById(R.id.list_hasil1);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface = ApiClient.getClient().create(ApiVotingInterface.class);
        Call<List<HasilVoting>> hasilVotingCall = mApiInterface.getHasilVoting();
        hasilVotingCall.enqueue(new Callback<List<HasilVoting>>() {
            @Override
            public void onResponse(Call<List<HasilVoting>> call, Response<List<HasilVoting>> response) {
                List<HasilVoting> hasilVotingList = response.body();
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
