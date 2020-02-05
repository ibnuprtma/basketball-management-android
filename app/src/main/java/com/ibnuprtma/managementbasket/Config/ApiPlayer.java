package com.ibnuprtma.managementbasket.Config;

import com.ibnuprtma.managementbasket.Model.GetPlayer;
import com.ibnuprtma.managementbasket.Model.PostPutDelPlayer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiPlayer {

    @GET("functions/get.php")
    Call<GetPlayer> getPlayer();

    @FormUrlEncoded
    @POST("functions/insert.php")
    Call<PostPutDelPlayer> postPlayer(@Field("name") String name,
                                    @Field("position") String position,
                                    @Field("number_jersey") String number_jersey,
                                    @Field("address") String address,
                                    @Field("images") String images);

    @FormUrlEncoded
    @POST("functions/update.php")
    Call<PostPutDelPlayer> putPlayer(@Field("id") int id,
                                 @Field("name") String name,
                                 @Field("position") String position,
                                 @Field("number_jersey") String number_jersey,
                                 @Field("address") String address);

    @FormUrlEncoded
    @POST("functions/delete.php")
    Call<PostPutDelPlayer> delPlayer(@Field("id") int id);
}
