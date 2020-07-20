package project.akbaralzaini.evoting.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import project.akbaralzaini.evoting.DetailKandidatActivity;
import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.model.Kandidat;

public class KandidatAdapter extends RecyclerView.Adapter<KandidatAdapter.MyViewHolder> {

    List<Kandidat> mKandidatList;

    public KandidatAdapter(List <Kandidat> KandiatList) {
        mKandidatList=KandiatList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.mTextViewNama.setText(mKandidatList.get(position).getNama());
        holder.mTextViewKelas.setText(mKandidatList.get(position).getKelas());
        holder.mTextViewUrut.setText(mKandidatList.get(position).getId());
       // holder.mImageViewFoto.setImageResource(mKandidatList.get(position).getFoto());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailKandidatActivity.class);
                i.putExtra("id",mKandidatList.get(position).getId());
                i.putExtra("nama",mKandidatList.get(position).getNama());
                i.putExtra("kelas",mKandidatList.get(position).getKelas());
                i.putExtra("nis",mKandidatList.get(position).getNis());
                i.putExtra("visi",mKandidatList.get(position).getVisi());
                i.putExtra("misi",mKandidatList.get(position).getMisi());
                i.putExtra("tanggal_lahir",mKandidatList.get(position).getTanggal_lahir());
                i.putExtra("pengalaman",mKandidatList.get(position).getPelngalaman());
                i.putExtra("foto",mKandidatList.get(position).getFoto());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKandidatList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNama, mTextViewKelas, mTextViewUrut;
        public ImageView mImageViewFoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNama = (TextView) itemView.findViewById(R.id.nama_kandidat);
            mTextViewKelas = (TextView) itemView.findViewById(R.id.kelas_kandidat);
            mTextViewUrut = (TextView) itemView.findViewById(R.id.nomor_kandidat);
            mImageViewFoto = (ImageView) itemView.findViewById(R.id.gambar_kandidat);
        }
    }


}
