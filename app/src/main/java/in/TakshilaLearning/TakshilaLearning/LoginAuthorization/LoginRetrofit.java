package in.TakshilaLearning.TakshilaLearning.LoginAuthorization;

import in.TakshilaLearning.TakshilaLearning.LoginAuthorization.model.AccessToken;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginRetrofit {



    @POST("/oauth/token")
    Call<AccessToken> getAccessToken(@Body AccessToken user);

/*
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

 */




}

