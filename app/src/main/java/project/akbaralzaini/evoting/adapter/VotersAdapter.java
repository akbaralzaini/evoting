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
import project.akbaralzaini.evoting.model.Voters;

public class VotersAdapter extends RecyclerView.Adapter<VotersAdapter.MyViewHolder>{

    List<Voters> mVotersList;

    public VotersAdapter(List<Voters> VotersList){
        this.mVotersList = VotersList;
    }


    @Override
    public VotersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_voters, parent, false);
        VotersAdapter.MyViewHolder mViewHolder = new VotersAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VotersAdapter.MyViewHolder holder, int position) {
        holder.nama_voters.setText(mVotersList.get(position).getNama());
        holder.tanggal_voters.setText(mVotersList.get(position).getTanggal_voting());
    }

    @Override
    public int getItemCount() {
        return mVotersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nama_voters,tanggal_voters;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_voters = itemView.findViewById(R.id.nama_voters);
            tanggal_voters = itemView.findViewById(R.id.tanggal_voters);
        }
    }
}
