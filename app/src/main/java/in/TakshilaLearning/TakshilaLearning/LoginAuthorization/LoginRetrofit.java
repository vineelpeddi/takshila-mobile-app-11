package in.TakshilaLearning.TakshilaLearning.LoginAuthorization;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginRetrofit {

    @GET("/oauth/me")
    Call<ResponseBody> getInfo(
            @Header("Authorization") String authToken);




    @Headers("Accept: application/json")
    @POST("/oauth/token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("grant_type") String authorization_code,
            @Field("code") String code,
            @Field("client_id") String ClientId,
            @Field("client_secret") String ClientSecret,
            @Field("redirect_uri") String redirectUri
                                      );


}

