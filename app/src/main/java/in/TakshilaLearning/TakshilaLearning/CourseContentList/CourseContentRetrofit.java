package in.TakshilaLearning.TakshilaLearning.CourseContentList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CourseContentRetrofit {
    @GET("wp-json/wplms/v1/course/25453")
    Call<ResponseBody> getCourse();

}
