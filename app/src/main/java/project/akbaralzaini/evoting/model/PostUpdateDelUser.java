package project.akbaralzaini.evoting.model;

import com.google.gson.annotations.SerializedName;

public class PostUpdateDelUser {
    @SerializedName("status") private String status;
    @SerializedName("result") private User mUser;
    @SerializedName("message") private String missage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public String getMissage() {
        return missage;
    }

    public void setMissage(String missage) {
        this.missage = missage;
    }
}
