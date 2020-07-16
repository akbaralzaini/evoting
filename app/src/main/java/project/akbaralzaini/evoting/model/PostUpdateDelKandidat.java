package project.akbaralzaini.evoting.model;

import com.google.gson.annotations.SerializedName;

public class PostUpdateDelKandidat {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Kandidat mKandidat;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Kandidat getKandidat() {
        return mKandidat;
    }
    public void setKandidat(Kandidat Kandidat) {
        mKandidat = Kandidat;
    }
}
