package project.akbaralzaini.evoting.Rest;

import java.util.List;

import project.akbaralzaini.evoting.model.Pasangan;
import project.akbaralzaini.evoting.model.PasanganLkp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiPasanganInterface {

    @GET("Pasangan/")
    Call<List<Pasangan>> getPasangan();

    @GET("Pasangan/")
    Call<List<PasanganLkp>> getPasanganLengkap();

    @POST("Pasangan/create")
    Call<Pasangan> postPasangan(@Body Pasangan pasangan);


    @PUT("Pasangan/update/{id}")
    Call<Pasangan> putKandidat(@Path("id") String id, @Body Pasangan pasangan);


    @DELETE("Pasangan/delete/{id}")
    Call<Pasangan> deletePasangan(@Path("id") String id);
}
