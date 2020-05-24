package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoursesForSaleRetrofit {
    @GET("/wp-json/wc/v3/products")
    Call<ResponseBody> getInfo(
            @Query("access_token") String authToken);
}
