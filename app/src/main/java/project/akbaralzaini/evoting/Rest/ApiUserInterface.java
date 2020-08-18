package project.akbaralzaini.evoting.Rest;

import android.util.Log;

import java.util.List;

import project.akbaralzaini.evoting.model.Login;
import project.akbaralzaini.evoting.model.PostUpdateDelUser;
import project.akbaralzaini.evoting.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiUserInterface {
    @GET("User/")
    Call<List<User>> getUser();

    @POST("User/create")
    Call<User> postUser(@Body User user);

    @PUT("User/update/{id}")
    Call<User> putUser( @Path("id") String id, @Body User user);


    @POST("Authentication/login")
    Call<User> loginRequest(@Body Login login);
}
