package project.akbaralzaini.evoting.model;

import com.google.gson.annotations.SerializedName;

public class HasilVoting {

    @SerializedName("nama_ketua") private String nama_ketua;
    @SerializedName("id_pasangan") private String id_pasangan;
    @SerializedName("no_urut") private String no_urut;
    @SerializedName("jumlah") private String jumlah;


    public String getNama_ketua() {
        return nama_ketua;
    }

    public void setNama_ketua(String nama_ketua) {
        this.nama_ketua = nama_ketua;
    }

    public String getId_pasangan() {
        return id_pasangan;
    }

    public void setId_pasangan(String id_pasangan) {
        this.id_pasangan = id_pasangan;
    }

    public String getNo_urut() {
        return no_urut;
    }

    public void setNo_urut(String no_urut) {
        this.no_urut = no_urut;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
