package project.akbaralzaini.evoting.model;

import com.google.gson.annotations.SerializedName;


public class Kandidat {

    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nis")
    private String nis;
    @SerializedName("kelas")
    private String kelas;
    @SerializedName("pengalaman")
    private String pengalaman;
    @SerializedName("foto")
    private String foto;

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

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPengalaman() {
        return pengalaman;
    }

    public void setPengalaman(String pengalaman) {
        this.pengalaman = pengalaman;
    }
}
