package com.deohrt.planet.API;

import com.deohrt.planet.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("nama") String nama,
            @Field("pengertian") String pengertian,
            @Field("ukuran") String ukuran,
            @Field("karakteristik") String karakteristik,
            @Field("sejarah") String sejarah
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("pengertian") String pengertian,
            @Field("ukuran") String ukuran,
            @Field("karakteristik") String karakteristik,
            @Field("sejarah") String sejarah
    );


    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardDelete(
            @Field("id") String id
    );

}
