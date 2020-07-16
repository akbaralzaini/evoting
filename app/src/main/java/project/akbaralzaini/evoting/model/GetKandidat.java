package project.akbaralzaini.evoting.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKandidat {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Kandidat> listDataKandidat;
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
    public List<Kandidat> getListDataKandidat() {
        return listDataKandidat;
    }
    public void setListDataKandidat(List<Kandidat> listDataKandidat) {
        this.listDataKandidat = listDataKandidat;
    }
}
