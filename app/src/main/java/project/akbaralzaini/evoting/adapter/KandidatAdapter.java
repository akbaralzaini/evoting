package project.akbaralzaini.evoting.adapter;

import project.akbaralzaini.evoting.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import project.akbaralzaini.evoting.model.Kandidat;

public class KandidatAdapter extends ArrayAdapter<Kandidat> {

    List<Kandidat> listKandidat;
    Context context;
    int layout;

    public KandidatAdapter(Context context, int layout, List<Kandidat> listKandidat) {
        super(context, layout, listKandidat);
        this.context=context;
        this.layout=layout;
        this.listKandidat=listKandidat;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        KandidatHolder holder;

        if(v==null){
            LayoutInflater vi=((Activity)context).getLayoutInflater();
            v=vi.inflate(layout, parent,false);

            holder=new KandidatHolder();
            holder.gambarView=(ImageView) v.findViewById(R.id.gambar_kandidat);
            holder.namaView=(TextView) v.findViewById(R.id.nama_kandidat);
            holder.kelasView=(TextView) v.findViewById(R.id.kelas_kandidat);
            holder.nomorView = (TextView) v.findViewById(R.id.nomor_kandidat);

            v.setTag(holder);
        }
        else{
            holder=(KandidatHolder) v.getTag();
        }

        Kandidat Kandidat=listKandidat.get(position);
        holder.gambarView.setImageResource(Kandidat.getFoto());
        holder.namaView.setText(Kandidat.getNama());
        holder.kelasView.setText(Kandidat.getKelas());
        holder.nomorView.setText(Kandidat.getNomor());

        return v;
    }

    static class KandidatHolder{
        ImageView gambarView;
        TextView namaView;
        TextView kelasView;
        TextView nomorView;
    }

}
