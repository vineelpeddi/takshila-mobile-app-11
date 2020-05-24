package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;


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
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCourseinfo(token);
        tv_courses_for_sale = (TextView)view.findViewById(R.id.tv_courses_for_sale);

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
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "1233 = " + response);

                if(response.isSuccessful()) {
                    try {
                        String Courses = response.body().string();
                        tv_courses_for_sale.setText(Courses);
                        Log.e(TAG, "1233 = " + response.body().string());
                    } catch (IOException e) {
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
