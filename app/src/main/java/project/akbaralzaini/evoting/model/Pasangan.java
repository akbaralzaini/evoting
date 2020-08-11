package project.akbaralzaini.evoting.model;

import com.google.gson.annotations.SerializedName;

public class Pasangan {
    @SerializedName("id")
    private String id;
    @SerializedName("id_ketua")
    private String id_ketua;
    @SerializedName("id_wakil")
    private String id_wakil;
    @SerializedName("visi")
    private String visi;
    @SerializedName("misi")
    private String misi;
    @SerializedName("no_urut")
    private String no_urut;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_ketua() {
        return id_ketua;
    }

    public void setId_ketua(String id_ketua) {
        this.id_ketua = id_ketua;
    }

    public String getId_wakil() {
        return id_wakil;
    }

    public void setId_wakil(String id_wakil) {
        this.id_wakil = id_wakil;
    }

    public String getVisi() {
        return visi;
    }

    public void setVisi(String visi) {
        this.visi = visi;
    }

    public String getMisi() {
        return misi;
    }

    public void setMisi(String misi) {
        this.misi = misi;
    }

    public String getNo_urut() {
        return no_urut;
    }

    public void setNo_urut(String no_urut) {
        this.no_urut = no_urut;
    }
}
