package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.RvCourseForSaleContentPojo;
import in.TakshilaLearning.TakshilaLearning.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.volley.VolleyLog.TAG;

public class CoursesForSaleFragment extends Fragment {
    String token;
    TextView tv_courses_for_sale;
    RecyclerView rv_courses_list;
    static int count;
    String Courses;
    static ArrayList<RvCourseForSaleContentPojo> CourseForSaleListData ;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View inflate =  inflater.inflate(R.layout.fragment_courses_for_sale, container,false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            token = bundle.getString("access_token"); // Key, default value
        }
        rv_courses_list = (RecyclerView) inflate.findViewById(R.id.rv_courses_list_);
        rv_courses_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_courses_list.setAdapter(new CoursesForSaleListAdapter(CourseForSaleListData));
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCourseinfo(token);

    }
    private void getCourseinfo(String token){
        Log.e(TAG, "1233 =" + token);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://dev123.takshi.la")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        CoursesForSaleRetrofit client = retrofit.create(CoursesForSaleRetrofit.class);
        Call<ResponseBody> call =  client.getInfo(
                token
        );
        call.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "1233 = " + response);

                if(response.isSuccessful()) {
                    try {
                        Courses = response.body().string();
                        CourseForSaleListData = new ArrayList<>();


                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject json = jsonArray.getJSONObject(0);
                        JSONObject data = json.getJSONObject("data");
                        JSONObject instructor = data.getJSONObject("instructor");
                        JSONObject description = instructor.getJSONObject("description");
                        JSONObject price = description.getJSONObject("price");
                        JSONObject demoUrl = price.getJSONObject("demoUrl");
                        JSONObject material = demoUrl.getJSONObject("material");

                        String instructor_name  = instructor.getString("name");
                        String description_name = description.getString("name");
                        String price_name = price.getString("name");
                        String demoUrl_name = demoUrl.getString("name");
                        String material_name = material.getString("name");

                        JSONArray curriculumArray = data.getJSONArray("curriculum");
                        // Log.e(TAG, "tv_view = " + instructor_name);
                        int ex = 0;
                        count = 0;
                        for (int j = 0; j < curriculumArray.length(); j++) {

                            JSONObject ingredObject = curriculumArray.getJSONObject(j);
                            String ingType = ingredObject.getString("type");
                            String ingCourse = ingredObject.getString("course");
                            //Log.e(TAG, "tv_view = " + ingType);
                            if (ex == 1 && ingType.equals("unit")) {
                                count = count + 1;
                                // Log.e(TAG, "ex = " + ingTitle);
                                RvCourseForSaleContentPojo obj = new RvCourseForSaleContentPojo();
                                obj.setInstructor(instructor_name);
                                obj.setDescription(description_name);
                                obj.setPrice(price_name);
                                obj.setDemoUrl(demoUrl_name);
                                obj.setMaterial(material_name);
                                CourseForSaleListData.add(obj);
                                // Log.e(TAG, "tv_view = " + CourseContentListData);

                            }

                            Log.e(TAG, "1233 = " + response.body().string());
                        }
                    }catch(IOException | JSONException e){
                            e.printStackTrace();
                        }



                }
                else{
                    Log.e(TAG, "1233 = " + "no");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "1233 = " + "fail");

            }
        });

    }
}
