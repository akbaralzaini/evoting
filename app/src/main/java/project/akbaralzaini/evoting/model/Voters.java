package project.akbaralzaini.evoting.model;

import com.google.gson.annotations.SerializedName;

public class Voters {
    @SerializedName("id") private String id;
    @SerializedName("id_pasangan") private String id_pasangan;
    @SerializedName("id_user") private String id_user;
    @SerializedName("tanggal_voting") private String tanggal_voting;
    @SerializedName("username") private String username;
    @SerializedName("nama") private String nama;
    @SerializedName("password") private String password;
    @SerializedName("role") private String role;
    @SerializedName("created_at") private String created_at;
    @SerializedName("updated_at") private String updated_at;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
