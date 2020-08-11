package project.akbaralzaini.evoting.Rest;

import java.util.List;

import okhttp3.ResponseBody;
import project.akbaralzaini.evoting.model.Pasangan;
import project.akbaralzaini.evoting.model.PasanganLkp;
import project.akbaralzaini.evoting.model.PostUpdateDelKandidat;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiPasanganInterface {

    @GET("pasangan/")
    Call<List<Pasangan>> getPasangan();

    @GET("pasangan/")
    Call<List<PasanganLkp>> getPasanganLengkap();

    @POST("pasangan/create")
    Call<Pasangan> postPasangan(@Body Pasangan pasangan);


    @PUT("pasangan/update/{id}")
    Call<Pasangan> putKandidat(@Path("id") String id, @Body Pasangan pasangan);


    @DELETE("pasangan/delete/{id}")
    Call<Pasangan> deletePasangan(@Path("id") String id);
}
