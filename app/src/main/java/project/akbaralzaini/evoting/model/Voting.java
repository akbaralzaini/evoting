package project.akbaralzaini.evoting.model;

import com.google.gson.annotations.SerializedName;

public class Voting {
    @SerializedName("id") private String id;
    @SerializedName("id_pasangan") private  String id_pasangan;
    @SerializedName("id_user") private String id_user;
    @SerializedName("tanggal_voting") private String tanggal_voting;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_pasangan() {
        return id_pasangan;
    }

    public void setId_pasangan(String id_pasangan) {
        this.id_pasangan = id_pasangan;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getTanggal_voting() {
        return tanggal_voting;
    }

    public void setTanggal_voting(String tanggal_voting) {
        this.tanggal_voting = tanggal_voting;
    }
}
