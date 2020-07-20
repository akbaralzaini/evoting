package project.akbaralzaini.evoting.Rest;

import java.util.List;

import project.akbaralzaini.evoting.model.PostUpdateDelUser;
import project.akbaralzaini.evoting.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiUserInterface {
    @GET("user/")
    Call<List<User>> getUser();
    @FormUrlEncoded
    @POST("user/create/")
    Call<PostUpdateDelUser> postUser(@Field("username") String username,
                                     @Field("nama") String nama,
                                     @Field("password") String password,
                                     @Field("role") String role,
                                     @Field("create_at") String create_at,
                                     @Field("update_at") String update_at);
    @FormUrlEncoded
    @PUT("user/put/")
    Call<PostUpdateDelUser> putUser( @Field("id") String id,
                                     @Field("username") String username,
                                     @Field("nama") String nama,
                                     @Field("password") String password,
                                     @Field("role") String role,
                                     @Field("create_at") String create_at,
                                     @Field("update_at") String update_at);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "user", hasBody = true)
    Call<PostUpdateDelUser> deleteUser(@Field("id") String id);
}
