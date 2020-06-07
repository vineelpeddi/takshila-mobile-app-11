package in.TakshilaLearning.TakshilaLearning.CourseContentList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.TakshilaLearning.TakshilaLearning.R;

import static com.android.volley.VolleyLog.TAG;

public class VideoCourseContentFragment extends Fragment {
    private static final String TAG = "abc";
    static String abc;
    static TextView tv_view;
    static Spinner sp_video;
    static View view;
    static ArrayAdapter<String> dataAdapter;
    List<String> list_options;
    private ArrayList<RvCourseContentPojo> CourseContentListData;
    static RvCourseContentListAdapter adapter;
    public RecyclerView recyclerView1;
    static LinearLayoutManager mLayoutManager;
    static int count;
    String Course;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_content, container, false);
        recyclerView1 = (RecyclerView)view.findViewById(R.id.recyclerView);
        Bundle bundle = this.getArguments();
        list_options = new ArrayList<String>();
        list_options = bundle.getStringArrayList("options");
        Course = bundle.getString("Course");
        sp_video = (Spinner)view.findViewById(R.id.spinner_video);
        adapter = new RvCourseContentListAdapter(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataAdapter = new ArrayAdapter<String>
                (this.getActivity(), android.R.layout.simple_spinner_item, list_options);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        sp_video.setAdapter(dataAdapter);
        sp_video.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = sp_video.getSelectedItem().toString();
                try {
                    getVideoContent(item,Course);
                    adapter.setListContent(CourseContentListData);
                    Log.e(TAG, "1233 = " + adapter);
                    recyclerView1.setAdapter(adapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView1.setLayoutManager(layoutManager);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void getVideoContent(String item,String response) throws IOException, JSONException {
        CourseContentListData = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response);
        JSONObject json = jsonArray.getJSONObject(0);
        JSONObject data = json.getJSONObject("data");
        JSONObject course = data.getJSONObject("course");
        JSONObject instructor = course.getJSONObject("instructor");
        String instructor_name = instructor.getString("name");
        JSONArray curriculumArray = data.getJSONArray("curriculum");
        int ex =0;
        count =0;
        for(int j=0;j<curriculumArray.length();j++){

            JSONObject ingredObject= curriculumArray.getJSONObject(j);
            String ingType = ingredObject.getString("type");
            String ingTitle = ingredObject.getString("title");
            //Log.e(TAG, "tv_view = " + ingType);
            if(ex==1 && ingType.equals("unit")){
                count =count +1;
                RvCourseContentPojo obj = new RvCourseContentPojo();
                obj.setInstructor(instructor_name);
                obj.setTitle(ingTitle);
                obj.setCourse("bank");
                CourseContentListData.add(obj);
            }
            if(ingTitle.equals(item)){
                Log.e(TAG, "tv_view = " + ex);
                ex = 1;
            }else if(!ingTitle.equals(item) && ingType.equals("section")) {
                ex = 0;
            }
    //        Log.e(TAG, "1233 = " + CourseContentListData.size());
            //Log.e(TAG, "tv_view = " + adapter);
        }

    }
}
