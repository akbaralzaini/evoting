package project.akbaralzaini.evoting.Rest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import project.akbaralzaini.evoting.model.Kandidat;
import project.akbaralzaini.evoting.model.Pasangan;
import project.akbaralzaini.evoting.model.PostUpdateDelKandidat;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("kandidat")
    Call<List<Kandidat>> getKandidat();

    @Multipart
    @POST("kandidat/create")
    Call<Kandidat> postKandidat(@Part MultipartBody.Part foto,
                                @Part("nama") RequestBody nama,
                                @Part("nis") RequestBody nis,
                                @Part("kelas") RequestBody kelas,
                                @Part("pengalaman") RequestBody pengalaman);


    @Multipart
    @POST("kandidat/update/{id}")
    Call<Kandidat> putKandidat(@Path("id") String id,
                               @Part MultipartBody.Part foto,
                               @Part("nama") RequestBody nama,
                               @Part("nis") RequestBody nis,
                               @Part("kelas") RequestBody kelas,
                               @Part("pengalaman") RequestBody pengalaman);


    @DELETE("kandidat/delete/{id}")
    Call<Kandidat> deleteKandidat(@Path("id") String id);
}
