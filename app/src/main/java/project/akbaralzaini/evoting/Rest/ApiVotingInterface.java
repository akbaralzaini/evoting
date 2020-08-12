package project.akbaralzaini.evoting.Rest;

import java.util.List;

import project.akbaralzaini.evoting.model.HasilVoting;
import project.akbaralzaini.evoting.model.User;
import project.akbaralzaini.evoting.model.Voting;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiVotingInterface {
    @GET("Voting/")
    Call<List<HasilVoting>> getHasilVoting();

    @POST("Voting/create")
    Call<Voting> Createvoting(@Body Voting voting);
}
