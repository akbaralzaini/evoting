package project.akbaralzaini.evoting.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.adminactivity.DetailKandidatActivity;
import project.akbaralzaini.evoting.adminactivity.DetailPasanganAdminActivity;
import project.akbaralzaini.evoting.model.Pasangan;
import project.akbaralzaini.evoting.model.PasanganLkp;
import project.akbaralzaini.evoting.siswaActivity.VotingActivity;
import project.akbaralzaini.evoting.util.SharedPrefManager;

public class PasanganAdapter extends RecyclerView.Adapter<PasanganAdapter.MyViewHolder> {

    List<PasanganLkp> mPasanganList;
    SharedPrefManager sharedPrefManager;
    private Context context;

    public PasanganAdapter(List<PasanganLkp> PasanganList,Context a){
        mPasanganList = PasanganList;
        this.context = a;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pasangan, parent, false);
        PasanganAdapter.MyViewHolder mViewHolder = new PasanganAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String url = "http://192.168.31.2/uploads/kandidat/"+mPasanganList.get(position).getId_ketua()+"/"+mPasanganList.get(position).getFoto_ketua();
        Picasso.get().load(url).resize(500,500).into(holder.mFotoKetua);

        String urlw = "http://192.168.31.2/uploads/kandidat/"+mPasanganList.get(position).getId_wakil()+"/"+mPasanganList.get(position).getFoto_wakil();
        Picasso.get().load(urlw).resize(500,500).into(holder.mFotoWakil);

        holder.mNomorUrut.setText("Nomor Urut " + mPasanganList.get(position).getNo_urut());
        holder.mNamaPasangan.setText(mPasanganList.get(position).getNama_ketua()+" & "+mPasanganList.get(position).getNama_wakil());
        holder.itemView.setOnClickListener(view -> {
            sharedPrefManager = new SharedPrefManager(context);
            if(sharedPrefManager.getRole().equals("admin")){
                Intent i = new Intent(view.getContext(), DetailPasanganAdminActivity.class);
                i.putExtra("id", mPasanganList.get(position).getId());
                i.putExtra("visi", mPasanganList.get(position).getVisi());
                i.putExtra("misi", mPasanganList.get(position).getMisi());
                i.putExtra("no_urut", mPasanganList.get(position).getNo_urut());
                i.putExtra("nama_ketua", mPasanganList.get(position).getNama_ketua());
                i.putExtra("pengalaman_ketua", mPasanganList.get(position).getPengalaman_ketua());
                i.putExtra("pengalaman_wakil", mPasanganList.get(position).getPengalaman_wakil());
                i.putExtra("foto_ketua", url);
                i.putExtra("foto_wakil", urlw);
                i.putExtra("nama_wakil", mPasanganList.get(position).getNama_wakil());
                i.putExtra("kelas_ketua", mPasanganList.get(position).getKelas_ketua());
                i.putExtra("kelas_wakil",mPasanganList.get(position).getKelas_wakil());
                i.putExtra("id_ketua",mPasanganList.get(position).getId_ketua());
                i.putExtra("id_wakil",mPasanganList.get(position).getId_wakil());
                view.getContext().startActivity(i);
            }
            else{
                Intent i = new Intent(view.getContext(), VotingActivity.class);
                i.putExtra("id", mPasanganList.get(position).getId());
                i.putExtra("visi", mPasanganList.get(position).getVisi());
                i.putExtra("misi", mPasanganList.get(position).getMisi());
                i.putExtra("no_urut", mPasanganList.get(position).getNo_urut());
                i.putExtra("nama_ketua", mPasanganList.get(position).getNama_ketua());
                i.putExtra("pengalaman_ketua", mPasanganList.get(position).getPengalaman_ketua());
                i.putExtra("pengalaman_wakil", mPasanganList.get(position).getPengalaman_wakil());
                i.putExtra("foto_ketua", url);
                i.putExtra("foto_wakil", urlw);
                i.putExtra("nama_wakil", mPasanganList.get(position).getNama_wakil());
                i.putExtra("kelas_ketua", mPasanganList.get(position).getKelas_ketua());
                i.putExtra("kelas_wakil",mPasanganList.get(position).getKelas_wakil());

                view.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mPasanganList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mNamaPasangan, mNomorUrut;
        public ImageView mFotoKetua,mFotoWakil;

        public MyViewHolder(View itemView) {
            super(itemView);
            mNamaPasangan = itemView.findViewById(R.id.nama_kandidat);
            mNomorUrut = itemView.findViewById(R.id.nomor_urut);

            mFotoKetua = itemView.findViewById(R.id.gambar_ketua);
            mFotoWakil = itemView.findViewById(R.id.gambar_wakil);
        }
    }
}
