package project.akbaralzaini.evoting.model;

import com.google.gson.annotations.SerializedName;


public class Kandidat {

    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nis")
    private String nis;
    @SerializedName("visi")
    private String visi;
    @SerializedName("misi")
    private String misi;
    @SerializedName("tanggal_lahir")
    private String tanggal_lahir;
    @SerializedName("kelas")
    private String kelas;
    @SerializedName("pengalaman")
    private String pelngalaman;
    @SerializedName("foto")
    private String foto;
    @SerializedName("hasil_vote")
    private int hasil_vote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
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

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getPelngalaman() {
        return pelngalaman;
    }

    public void setPelngalaman(String pelngalaman) {
        this.pelngalaman = pelngalaman;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getHasil_vote() {
        return hasil_vote;
    }

    public void setHasil_vote(int hasil_vote) {
        this.hasil_vote = hasil_vote;
    }
}
