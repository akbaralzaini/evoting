package project.akbaralzaini.evoting.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.model.HasilVoting;

public class HasilAdapter extends RecyclerView.Adapter<HasilAdapter.MyViewHolder>{

    List<HasilVoting> mHasilList;

    public HasilAdapter(List<HasilVoting> HasilList){
        this.mHasilList = HasilList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hasil, parent, false);
        HasilAdapter.MyViewHolder mViewHolder = new HasilAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nomor_urut.setText("Nomor Urut : "+mHasilList.get(position).getNo_urut());
        holder.nama_ketua.setText(mHasilList.get(position).getNama_ketua());
        holder.hasil.setText(mHasilList.get(position).getJumlah());

    }

    @Override
    public int getItemCount() {
        return mHasilList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nomor_urut,nama_ketua,hasil;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomor_urut = itemView.findViewById(R.id.no_urutl);
            nama_ketua = itemView.findViewById(R.id.nama_ketual);
            hasil = itemView.findViewById(R.id.hasil);
        }
    }
}
