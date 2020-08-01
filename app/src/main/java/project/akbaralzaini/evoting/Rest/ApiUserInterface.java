package project.akbaralzaini.evoting.Rest;

import java.util.List;

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

public interface ApiUserInterface {
    @GET("User/")
    Call<List<User>> getUser();
    @POST("User/create")
    Call<User> postUser(@Body User user);
    @FormUrlEncoded
    @PUT("user/put/{id}")
    Call<PostUpdateDelUser> putUser( @Field("id") String id,
                                     @Field("username") String username,
                                     @Field("nama") String nama,
                                     @Field("password") String password,
                                     @Field("role") String role,
                                     @Field("create_at") String create_at,
                                     @Field("update_at") String update_at);

    @GET("login/")
    Call<User> loginRequest(@Body User user);
}
