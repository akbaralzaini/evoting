package project.akbaralzaini.evoting.model;

import com.google.gson.annotations.SerializedName;

public class PasanganLkp {
    @SerializedName("id") private String id;
    @SerializedName("visi") private String visi;
    @SerializedName("misi") private String misi;
    @SerializedName("no_urut") private String no_urut;
    @SerializedName("nama_ketua") private String nama_ketua;
    @SerializedName("pengalaman_ketua") private String pengalaman_ketua;
    @SerializedName("pengalaman_wakil") private String pengalaman_wakil;
    @SerializedName("foto_ketua") private String foto_ketua;
    @SerializedName("foto_wakil") private String foto_wakil;
    @SerializedName("nama_wakil") private String nama_wakil;
    @SerializedName("id_ketua") private String id_ketua;
    @SerializedName("id_wakil") private String id_wakil;
    @SerializedName("kelas_ketua") private String kelas_ketua;
    @SerializedName("kelas_wakil") private String kelas_wakil;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_wakil() {
        return nama_wakil;
    }

    public void setNama_wakil(String nama_wakil) {
        this.nama_wakil = nama_wakil;
    }

    public String getFoto_wakil() {
        return foto_wakil;
    }

    public void setFoto_wakil(String foto_wakil) {
        this.foto_wakil = foto_wakil;
    }

    public String getFoto_ketua() {
        return foto_ketua;
    }

    public void setFoto_ketua(String foto_ketua) {
        this.foto_ketua = foto_ketua;
    }

    public String getPengalaman_wakil() {
        return pengalaman_wakil;
    }

    public void setPengalaman_wakil(String pengalaman_wakil) {
        this.pengalaman_wakil = pengalaman_wakil;
    }

    public String getPengalaman_ketua() {
        return pengalaman_ketua;
    }

    public void setPengalaman_ketua(String pengalaman_ketua) {
        this.pengalaman_ketua = pengalaman_ketua;
    }

    public String getNama_ketua() {
        return nama_ketua;
    }

    public void setNama_ketua(String nama_ketua) {
        this.nama_ketua = nama_ketua;
    }

    public String getNo_urut() {
        return no_urut;
    }

    public void setNo_urut(String no_urut) {
        this.no_urut = no_urut;
    }

    public String getMisi() {
        return misi;
    }

    public void setMisi(String misi) {
        this.misi = misi;
    }

    public String getVisi() {
        return visi;
    }

    public void setVisi(String visi) {
        this.visi = visi;
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

    public String getKelas_ketua() {
        return kelas_ketua;
    }

    public void setKelas_ketua(String kelas_ketua) {
        this.kelas_ketua = kelas_ketua;
    }

    public String getKelas_wakil() {
        return kelas_wakil;
    }

    public void setKelas_wakil(String kelas_wakil) {
        this.kelas_wakil = kelas_wakil;
    }
}
