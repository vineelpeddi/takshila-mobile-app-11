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
    RecyclerView rv_courses_list;
    ArrayList<RvCourseForSaleContentPojo> CourseForSaleListData;
    static CoursesForSaleListAdapter adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate =  inflater.inflate(R.layout.fragment_courses_for_sale, container,false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            token = bundle.getString("access_token"); // Key, default value
        }
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  getCourseinfo(token);
        rv_courses_list = (RecyclerView)view.findViewById(R.id.rv_courses_for_sale_list);
        adapter = new CoursesForSaleListAdapter(getActivity());
        getMockCourseInfo();


    }

    private void getMockCourseInfo() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://www.amock.io/api/maitreyi/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        CoursesForSaleRetrofit client = retrofit.create(CoursesForSaleRetrofit.class);
        Call<ResponseBody> call =  client.getMockInfo();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               if(response.isSuccessful()){
                   try {
                       String Courses = response.body().string();
                       GetCourseForSaleList(Courses);
                       Log.e(TAG, "xyz " + CourseForSaleListData);
                       adapter.setListContent(CourseForSaleListData);
                       rv_courses_list.setAdapter(adapter);
                       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                       rv_courses_list.setLayoutManager(layoutManager);

                   }catch(IOException | JSONException e){
                       e.printStackTrace();
                   }

               }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

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
                        String Courses = response.body().string();
                        GetCourseForSaleList(Courses);


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

   public void GetCourseForSaleList(String Courses) throws JSONException {
       CourseForSaleListData = new ArrayList<>();
       JSONArray jsonArray = new JSONArray(Courses);
       for (int k = 0; k<jsonArray.length(); k++){
           JSONObject json     = jsonArray.getJSONObject(k);
           String id_name          = json.getString("id");
           String name_name        = json.getString("name");
           String description_name = json.getString("short_description");
           String price_name       = json.getString("price");
           String teacher_name     = json.getString("teacher");

           RvCourseForSaleContentPojo obj = new RvCourseForSaleContentPojo();

           obj.setId(id_name);
           obj.setName(name_name);
           obj.setDescription(description_name);
           obj.setPrice(price_name);
           obj.setTeacher(teacher_name);

           CourseForSaleListData.add(obj);

       }


    }
}
