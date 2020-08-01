package project.akbaralzaini.evoting.Rest;

import java.util.List;

import project.akbaralzaini.evoting.model.Kandidat;
import project.akbaralzaini.evoting.model.PostUpdateDelKandidat;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {
    @GET("kandidat")
    Call<List<Kandidat>> getKandidat();
    @FormUrlEncoded
    @POST("kandidat/post")
    Call<PostUpdateDelKandidat> postKandidat(    @Field("nama") String nama,
                                                 @Field("kelas") String kelas,
                                                 @Field("nis") String nis,
                                                 @Field("visi") String visi,
                                                 @Field("misi") String misi,
                                                 @Field("tanggal_lahir") String tanggal_lahir,
                                                 @Field("pengalaman") String pelngalaman,
                                                 @Field("foto") int foto);
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
