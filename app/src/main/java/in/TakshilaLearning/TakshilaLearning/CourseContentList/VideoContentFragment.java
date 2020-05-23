package in.TakshilaLearning.TakshilaLearning.CourseContentList;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import in.TakshilaLearning.TakshilaLearning.R;

public class VideoContentFragment extends Fragment {
    private static final String TAG = "abc";
    static String abc;
    static TextView tv_view;
    static Spinner sp_video;
    static View view;
    static ArrayAdapter<String> dataAdapter;
    List<String> list_options;
    static ArrayList<RvCourseContentPojo> CourseContentListData;
    static RvCourseContentListAdapter adapter;
    static RecyclerView recyclerView;
    static LinearLayoutManager mLayoutManager;
    static int count;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard


        view = inflater.inflate(R.layout.fragment_video_content, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        Bundle bundle = this.getArguments();
        list_options = new ArrayList<String>();
        list_options = bundle.getStringArrayList("options");
       // Log.e(TAG, "tv_view = " + list_options);
        dataAdapter = new ArrayAdapter<String>
                (this.getActivity(), android.R.layout.simple_spinner_item, list_options);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        sp_video = (Spinner)view.findViewById(R.id.spinner_video);
        sp_video.setAdapter(dataAdapter);
        sp_video.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                adapter = new RvCourseContentListAdapter(getActivity());
                String item = sp_video.getSelectedItem().toString();
                new Thread(() -> {
                    try {
                        Log.e(TAG, "tv_view = " + item);
                        getVideoContent(item);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }).start();
                try
                {
                    Thread.sleep(2000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                Log.i(TAG, "response: " + CourseContentListData);
                adapter.setListContent(CourseContentListData);

                recyclerView.setAdapter(adapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_view = (TextView)view.findViewById(R.id.tv_video);

    }


    public static void getVideoContent(String item) throws IOException, JSONException {

        final String SAMPLE_OTP_PLAYBACK_INFO_URL = "https://takshilalearning.com/wp-json/wplms/v1/course/25453";
        URL url = new URL(SAMPLE_OTP_PLAYBACK_INFO_URL);
        final HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();


        if (responseCode == 200) {
            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inLine;
            StringBuffer responseBuffer = new StringBuffer();

            while ((inLine = br.readLine()) != null) {
                responseBuffer.append(inLine);
            }
            br.close();

            String response = responseBuffer.toString();
            //Log.i(TAG, "response: " + response);
            CourseContentListData = new ArrayList<>();


            JSONArray jsonArray = new JSONArray(response);
            JSONObject json = jsonArray.getJSONObject(0);
            JSONObject data = json.getJSONObject("data");
            JSONObject course = data.getJSONObject("course");
            JSONObject instructor = course.getJSONObject("instructor");
            String instructor_name = instructor.getString("name");
            JSONArray curriculumArray = data.getJSONArray("curriculum");
           // Log.e(TAG, "tv_view = " + instructor_name);
            int ex =0;
            count =0;
            for(int j=0;j<curriculumArray.length();j++){

                JSONObject ingredObject= curriculumArray.getJSONObject(j);
                String ingType = ingredObject.getString("type");
                String ingTitle = ingredObject.getString("title");
                //Log.e(TAG, "tv_view = " + ingType);
                if(ex==1 && ingType.equals("unit")){
                    count =count +1;
                   // Log.e(TAG, "ex = " + ingTitle);
                    RvCourseContentPojo obj = new RvCourseContentPojo();
                    obj.setInstructor(instructor_name);
                    obj.setTitle(ingTitle);
                    obj.setCourse("bank");
                    CourseContentListData.add(obj);
                   // Log.e(TAG, "tv_view = " + CourseContentListData);

                }

                if(ingTitle.equals(item)){
                    Log.e(TAG, "tv_view = " + ex);
                    ex = 1;
                }else if(!ingTitle.equals(item) && ingType.equals("section")) {
                    ex = 0;
                }
                Log.e(TAG, "1233 = " + CourseContentListData.size());

                //Log.e(TAG, "tv_view = " + adapter);

            }




        } else {
            Log.e(TAG, "error response code = " + responseCode);
            throw new IOException("Network error, code " + responseCode);
        }

    }
}
