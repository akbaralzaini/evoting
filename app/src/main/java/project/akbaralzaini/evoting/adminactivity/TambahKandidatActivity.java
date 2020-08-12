package project.akbaralzaini.evoting.adminactivity;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.Rest.ApiClient;
import project.akbaralzaini.evoting.Rest.ApiInterface;
import project.akbaralzaini.evoting.model.Kandidat;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO : Restfull untuk create belum di buat.
public class TambahKandidatActivity extends Activity implements EasyPermissions.PermissionCallbacks{

    EditText mNama,mKelas, mTanggalLahir, mVisi, mMisi, mPengalaman, mNis;
    Button btnSimpan;
    TextView btnBack;
    ApiInterface mApiInterface;
    String file_path="";
    TextView btnFoto;
    MultipartBody.Part fileToUpload;

    private static final String TAG = TambahKandidatActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "Path_to_your_server";
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kandidat);

        mNama = findViewById(R.id.nama_kandidat);
        mKelas = findViewById(R.id.kelas_kandidat);
        mTanggalLahir = findViewById(R.id.ttl_kandidat);
        mPengalaman = findViewById(R.id.pengalaman_kandidat);
        mVisi = findViewById(R.id.visi_kandidat);
        mMisi = findViewById(R.id.misi_kandidat);
        mNis = findViewById(R.id.nisn_kandidat);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnSimpan = findViewById(R.id.button_simpan);
        btnBack = findViewById(R.id.button_batal);

        btnFoto = findViewById(R.id.foto_kandidat);



        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            }
        });

        btnSimpan.setOnClickListener(view -> {
            saveData();
//            //TODO : BENERI UPLOAD IMAGE
//            RequestBody nama = RequestBody.create(MediaType.parse("text/plain"), mNama.getText().toString());
//            RequestBody nis = RequestBody.create(MediaType.parse("text/plain"), mNis.getText().toString());
//            RequestBody kelas = RequestBody.create(MediaType.parse("text/plain"), mKelas.getText().toString());
//            RequestBody pengalaman = RequestBody.create(MediaType.parse("text/plain"), mPengalaman.getText().toString());
//
//
//            Call<Kandidat> postUpdateDelKandidatCall = mApiInterface.postKandidat(,nama,nis,kelas,pengalaman);
//            postUpdateDelKandidatCall.enqueue(new Callback<Kandidat>() {
//                @Override
//                public void onResponse(Call<Kandidat> call, Response<Kandidat> response) {
//                    Kandidat kandidat = response.body();
//                    //Log.e("S",kandidat.getNama());
//                    AlertDialog.Builder builder = new AlertDialog.Builder(TambahKandidatActivity.this);
//                    builder.setTitle("Informasi");
//                    builder.setMessage("Data Berhasil ditambahkan");
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            DashboardActivity.ma.refresh();
//                            finish();
//                        }
//                    });
//                    builder.show();
//                }
//
//                @Override
//                public void onFailure(Call<Kandidat> call, Throwable t) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(TambahKandidatActivity.this);
//                    builder.setTitle("Informasi");
//                    builder.setMessage("Data Gagal ditambahkan");
//                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Log.e("Gagal","Gagal Menambahkan");
//                        }
//                    });
//                    builder.show();
//                    //Toast.makeText(TambahKandidatActivity.this, "Error",Toast.LENGTH_LONG).show();
//                }
//            });
        });

        btnBack.setOnClickListener(view -> {
            DashboardActivity.ma.refresh();
            finish();
        });

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, TambahKandidatActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, TambahKandidatActivity.this);
                File file = new File(filePath);
                Log.d(TAG, "Filename " + file.getName());
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                fileToUpload = MultipartBody.Part.createFormData("foto", file.getName(), RequestBody.create(MediaType.parse("image/*"),file));

                file_path = file.getPath();
                btnFoto.setText(file.getName());
            }else{
                EasyPermissions.requestPermissions(this, "Memerlukan akases File", READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, TambahKandidatActivity.this);
            File file = new File(filePath);
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
            fileToUpload = MultipartBody.Part.createFormData("foto", file.getName(), mFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

            btnFoto.setText(file.getName());

        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }

    private void saveData() {
        //TODO : BENERI UPLOAD IMAGE
        RequestBody nama = RequestBody.create(MediaType.parse("text/plain"), mNama.getText().toString());
        RequestBody nis = RequestBody.create(MediaType.parse("text/plain"), mNis.getText().toString());
        RequestBody kelas = RequestBody.create(MediaType.parse("text/plain"), mKelas.getText().toString());
        RequestBody pengalaman = RequestBody.create(MediaType.parse("text/plain"), mPengalaman.getText().toString());
        File file = new File(file_path);

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part parts = MultipartBody.Part.createFormData("foto", file.getName(), requestBody);

        Call<Kandidat> postUpdateDelKandidatCall = mApiInterface.postKandidat(parts,nama,nis,kelas,pengalaman);
        postUpdateDelKandidatCall.enqueue(new Callback<Kandidat>() {
            @Override
            public void onResponse(Call<Kandidat> call, Response<Kandidat> response) {
                Kandidat kandidat = response.body();
                //Log.e("S",kandidat.getNama());
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahKandidatActivity.this);
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
            public void onFailure(Call<Kandidat> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahKandidatActivity.this);
                builder.setTitle("Informasi");
                builder.setMessage("Data Gagal ditambahkan");
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("Gagal","Gagal Menambahkan");
                    }
                });
                builder.show();
                //Toast.makeText(TambahKandidatActivity.this, "Error",Toast.LENGTH_LONG).show();
            }
        });
    }

}
