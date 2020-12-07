package project.akbaralzaini.evoting.adminactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

public class EditPasActivity extends Activity {

    ApiInterface mApiInterface;
    ApiPasanganInterface mApiPasangan;
    Spinner sKetua,sWakil;
    String idKetua,idWakil;
    int iKetua,iWakil;
    TextView tVisi,tMisi, tNomorUrut;
    String nama_ketua,nama_wakil;
    Button bSimpan;
    String id;


    public static EditPasActivity ma;
    List<String> listid = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pas);




        sKetua = findViewById(R.id.nama_ketua);
        sWakil = findViewById(R.id.nama_wakil);
        bSimpan = findViewById(R.id.button_simpanp);
        tVisi = findViewById(R.id.visi_kandidat);
        tMisi = findViewById(R.id.misi_kandidat);
        tNomorUrut = findViewById(R.id.nomor_urut);


        Intent mIntent = getIntent();
        id = mIntent.getStringExtra("id");
        tVisi.setText(mIntent.getStringExtra("visi"));
        tMisi.setText(mIntent.getStringExtra("misi"));
        tNomorUrut.setText(mIntent.getStringExtra("no_urut"));
        nama_ketua = mIntent.getStringExtra("nama_ketua");
        nama_wakil = mIntent.getStringExtra("nama_wakil");

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

                if (nama_ketua != null) {
                    int spinnerPosition = adapter.getPosition(nama_ketua);
                    sKetua.setSelection(spinnerPosition);
                }

                if (nama_wakil != null) {
                    int spinnerPosition = adapter.getPosition(nama_wakil);
                    sWakil.setSelection(spinnerPosition);
                }



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


                Call<Pasangan> pasanganCall = mApiPasangan.putKandidat(id,p);
                pasanganCall.enqueue(new Callback<Pasangan>() {
                    @Override
                    public void onResponse(Call<Pasangan> call, Response<Pasangan> response) {
                        if (response.body()==null){
                            AlertDialog.Builder builder = new AlertDialog.Builder(ma);
                            builder.setTitle("Informasi");
                            builder.setMessage("Data Gagal ditambahkan karena no urut sudah digunakan");
                            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Log.e("Gagal","Gagal Update");
                                }
                            });
                            builder.show();
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(ma);
                            builder.setTitle("Informasi");
                            builder.setMessage("Data Berhasil Disimpan");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent det = new Intent(EditPasActivity.this, DashboardActivity.class);
                                    startActivity(det);
                                    finish();
                                }
                            });
                            builder.show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Pasangan> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ma);
                        builder.setTitle("Informasi");
                        builder.setMessage("Data Gagal ditambahkan");
                        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.e("Gagal","Gagal Update");
                            }
                        });
                        builder.show();
                    }
                });


            }
        });

        ImageView back = findViewById(R.id.backicon);
        back.setOnClickListener(v -> finish());
    }
}
