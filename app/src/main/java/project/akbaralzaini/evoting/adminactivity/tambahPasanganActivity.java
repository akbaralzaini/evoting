package project.akbaralzaini.evoting.adminactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiInterface;
import project.akbaralzaini.evoting.Rest.ApiPasanganInterface;
import project.akbaralzaini.evoting.model.Kandidat;
import project.akbaralzaini.evoting.model.Pasangan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tambahPasanganActivity extends Activity {


    ApiInterface mApiInterface;
    ApiPasanganInterface mApiPasangan;
    Spinner sKetua,sWakil;
    String idKetua,idWakil;
    int iKetua,iWakil;
    TextView tVisi,tMisi, tNomorUrut;
    Button bSimpan;

    public static tambahPasanganActivity ma;
    List<String> listid = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pasangan);

        sKetua = findViewById(R.id.nama_ketua);
        sWakil = findViewById(R.id.nama_wakil);
        bSimpan = findViewById(R.id.button_simpanp);
        tVisi = findViewById(R.id.visi_kandidat);
        tMisi = findViewById(R.id.misi_kandidat);
        tNomorUrut = findViewById(R.id.nomor_urut);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mApiPasangan = ApiClient.getClient().create(ApiPasanganInterface.class);
        ma=this;


        Call<List<Kandidat>> kandidatCall = mApiInterface.getKandidat();
        kandidatCall.enqueue(new Callback<List<Kandidat>>() {
            @Override
            public void onResponse(Call<List<Kandidat>> call, Response<List<Kandidat>> response) {
                List<Kandidat> KandidatList = response.body();
                List<String> listSpinner = new ArrayList<String>();


                idKetua ="";
                for (int i=0;i<KandidatList.size();i++){
                    listSpinner.add(KandidatList.get(i).getNama());
                    listid.add(KandidatList.get(i).getId());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ma, android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sKetua.setAdapter(adapter);
                sWakil.setAdapter(adapter);



            }

            @Override
            public void onFailure(Call<List<Kandidat>> call, Throwable t) {
            }
        });

        bSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pasangan p = new Pasangan();
                iKetua = sKetua.getSelectedItemPosition();
                idKetua = listid.get(iKetua);

                iWakil = sWakil.getSelectedItemPosition();
                idWakil = listid.get(iWakil);

                p.setId_ketua(idKetua);
                p.setId_wakil(idWakil);
                p.setVisi(tVisi.getText().toString());
                p.setMisi(tMisi.getText().toString());
                p.setNo_urut(tNomorUrut.getText().toString());


                Call<Pasangan> pasanganCall = mApiPasangan.postPasangan(p);
                pasanganCall.enqueue(new Callback<Pasangan>() {
                    @Override
                    public void onResponse(Call<Pasangan> call, Response<Pasangan> response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(tambahPasanganActivity.this);
                        builder.setTitle("Informasi");
                        builder.setMessage("Data Berhasil ditambahkan");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DashboardActivity.ma.refresh();
                                finish();
                            }
                        });
                        builder.show();
                    }

                    @Override
                    public void onFailure(Call<Pasangan> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(tambahPasanganActivity.this);
                        builder.setTitle("Informasi");
                        builder.setMessage("Data Gagal ditambahkan");
                        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.e("Gagal","Gagal Menambahkan");
                            }
                        });
                        builder.show();
                    }
                });


            }
        });


    }


}
