package in.TakshilaLearning.TakshilaLearning.CourseContentList;



import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
import java.util.Collections;
import java.util.List;

import in.TakshilaLearning.TakshilaLearning.R;

public class CourseContent extends AppCompatActivity {
    private static final String TAG = "CActivity" ;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    static List<String> list_options;
    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);
      //  selectedFragment = new VideoContentFragment();
        //fragmentManager = getFragmentManager();
        //fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.fragment_content, selectedFragment);
        //fragmentTransaction.commit();

    }

   public void handleChangeFragment(View view) throws IOException, JSONException {

       if(view == findViewById(R.id.button_videos)){
           new Thread(() -> {
               try {

                   getVideoContent();

               } catch (IOException | JSONException e) {
                   e.printStackTrace();
               }
           }).start();
           Bundle bundle = new Bundle();
           while(list_options == null){

           }
           bundle.putStringArrayList("options", (ArrayList<String>) list_options);
           //Log.e(TAG, "tv_view = " + list_options);
           selectedFragment = new VideoContentFragment();
           selectedFragment.setArguments(bundle);
           fragmentManager = getFragmentManager();
           fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.replace(R.id.fragment_content, selectedFragment);
           fragmentTransaction.commit();
       } else if (view == findViewById(R.id.button_materials)) {
           selectedFragment = new MaterialContentFragment();
           fragmentManager = getFragmentManager();
           fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.replace(R.id.fragment_content, selectedFragment);
           fragmentTransaction.commit();
       }
   }
    public static void getVideoContent() throws IOException, JSONException {

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
            Log.i(TAG, "response: " + response);
            list_options = new ArrayList<String>();

            JSONArray jsonArray = new JSONArray(response);
            JSONObject json = jsonArray.getJSONObject(0);
            JSONObject data = json.getJSONObject("data");
            JSONArray curriculumArray = data.getJSONArray("curriculum");
            for(int j=0;j<curriculumArray.length();j++){
                JSONObject ingredObject= curriculumArray.getJSONObject(j);
                String ingType = ingredObject.getString("type");
                //Log.e(TAG, "tv_view = " + ingType);
                if(ingType.equals("section")){
                    String ingTitle = ingredObject.getString("title");
                    //Log.e(TAG, "tv_view = " + ingTitle);
                    list_options.add(ingTitle);
                }
            }
            Collections.sort(list_options);



        } else {
            Log.e(TAG, "error response code = " + responseCode);
            throw new IOException("Network error, code " + responseCode);
        }

    }
}
