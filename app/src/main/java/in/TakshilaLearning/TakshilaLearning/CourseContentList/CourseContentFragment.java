package in.TakshilaLearning.TakshilaLearning.CourseContentList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CourseDetails.CourseForSaleMaterialFragment;
import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CoursesForSaleRetrofit;
import in.TakshilaLearning.TakshilaLearning.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.volley.VolleyLog.TAG;

public class CourseContentFragment extends Fragment {
    static List<String> list_options;
    String Course;
    Button btn_video, btn_material;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_course_content, container,false);
        btn_video = (Button)v.findViewById(R.id.btn_videos);
        btn_material = (Button)v.findViewById(R.id.btn_material);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getVideoContent();
        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVideoContent();

            }
        });
    }

    private void getVideoContent() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://takshilalearning.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        CourseContentRetrofit client = retrofit.create(CourseContentRetrofit.class);
        Call<ResponseBody> call =  client.getCourse();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        Course = response.body().string();
                        list_options = new ArrayList<String>();
                        JSONArray jsonArray = new JSONArray(Course);
                        JSONObject json = jsonArray.getJSONObject(0);
                        JSONObject data = json.getJSONObject("data");
                        JSONArray curriculumArray = data.getJSONArray("curriculum");
                        for(int j=0;j<curriculumArray.length();j++){
                            JSONObject ingredObject= curriculumArray.getJSONObject(j);
                            String ingType = ingredObject.getString("type");
                            if(ingType.equals("section")){
                                String ingTitle = ingredObject.getString("title");
                                list_options.add(ingTitle);
                            }
                        }
                        Collections.sort(list_options);
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("options", (ArrayList<String>) list_options);
                        bundle.putString("Course",Course);
                        Log.e(TAG, "tv_view = " + list_options);
                        Fragment childfragmentVideo = new VideoCourseContentFragment();
                        childfragmentVideo.setArguments(bundle);
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_content_info, childfragmentVideo);
                        transaction.commit();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
