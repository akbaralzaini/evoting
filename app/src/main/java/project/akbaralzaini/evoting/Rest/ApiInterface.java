package project.akbaralzaini.evoting.Rest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import project.akbaralzaini.evoting.model.Kandidat;
import project.akbaralzaini.evoting.model.PostUpdateDelKandidat;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

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


    @FormUrlEncoded
    //TODO : dokumentasi put belum ada
    @PUT("kandidat/put")
    Call<PostUpdateDelKandidat> putKandidat(    @Field("id") String id,
                                                @Field("nama") String nama,
                                                @Field("kelas") String kelas,
                                                @Field("nis") String nis,
                                                @Field("visi") String visi,
                                                @Field("misi") String misi,
                                                @Field("tanggal_lahir") String tanggal_lahir,
                                                @Field("pengalaman") String pelngalaman,
                                                @Field("foto") int foto);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kandidat", hasBody = true)
    Call<PostUpdateDelKandidat> deleteKandidat(@Field("id") String id);
}
