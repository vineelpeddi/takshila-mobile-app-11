package in.TakshilaLearning.TakshilaLearning.CourseVideo;

import android.util.Log;
import android.util.Pair;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.vdocipher.aegis.player.VdoPlayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    private static final String TAG = "in.TakshilaLearning.TakshilaLearning.Course.Utils";

    static JSONObject jobj = new JSONObject();

    static String digitalClockTime(int timeInMilliSeconds) {
        int totalSeconds = timeInMilliSeconds/1000;
        int hours = totalSeconds / (60 * 60);
        int minutes = (totalSeconds - hours * 60 * 60) / 60;
        int seconds = (totalSeconds - hours * 60 * 60 - minutes * 60);

        String timeThumb = "";
        if (hours > 0) {
            if (hours < 10) {
                timeThumb += "0" + hours + ":";
            } else {
                timeThumb += hours + ":";
            }
        }
        if (minutes > 0) {
            if (minutes < 10) {
                timeThumb += "0" + minutes + ":";
            } else {
                timeThumb += minutes + ":";
            }
        } else {
            timeThumb += "00" + ":";
        }
        if (seconds < 10) {
            timeThumb += "0" + seconds;
        } else {
            timeThumb += seconds;
        }
        return timeThumb;
    }

    /**
     * @return index of number in provided array closest to the provided number
     */
    public static int getClosestFloatIndex(float[] refArray, float comp) {
        float distance = Math.abs(refArray[0] - comp);
        int index = 0;
        for (int i = 1; i < refArray.length; i++) {
            float currDistance = Math.abs(refArray[i] - comp);
            if (currDistance < distance) {
                index = i;
                distance = currDistance;
            }
        }
        return index;
    }

    public static String playbackStateString(boolean playWhenReady, int playbackState) {
        String stateName;
        switch (playbackState) {
            case VdoPlayer.STATE_IDLE:
                stateName = "STATE_IDLE";
                break;
            case VdoPlayer.STATE_READY:
                stateName = "STATE_READY";
                break;
            case VdoPlayer.STATE_BUFFERING:
                stateName = "STATE_BUFFERING";
                break;
            case VdoPlayer.STATE_ENDED:
                stateName = "STATE_ENDED";
                break;
            default:
                stateName = "STATE_UNKNOWN";
        }
        return "playWhenReady " + (playWhenReady ? "true" : "false") + ", " + stateName;
    }

    public static String getSizeString(int bitsPerSec, long millisec) {
        double sizeMB = ((double)bitsPerSec / (8 * 1024 * 1024)) * (millisec / 1000);
        return round(sizeMB, 2) + " MB";
    }

    public static long getSizeBytes(int bitsPerSec, long millisec) {
        return (bitsPerSec / 8) * (millisec / 1000);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
    // call on non-ui thread only
    public static Pair<String, String> getSampleOtpAndPlaybackInfo() throws IOException, JSONException {

        final String SAMPLE_OTP_PLAYBACK_INFO_URL = "https://dev.vdocipher.com/api/site/homepage_video";
        //final String SAMPLE_OTP_PLAYBACK_INFO_URL = "https://dev.vdocipher.com/api/videos/4cf3d43f8acb8b8db748a8cfd6711924/otp";
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

            JSONObject jObj = new JSONObject(response);
            String otp = jObj.getString("otp");
            //String otp = "f79efb3ad6a1b9b58a75c75100a4f08" ;
            String playbackInfo = jObj.getString("playbackInfo");
            return Pair.create(otp, playbackInfo);
        } else {
            Log.e(TAG, "error response code = " + responseCode);
            throw new IOException("Network error, code " + responseCode);
        }

    }
    **/
    public static Pair<String, String> getSampleOtpAndPlaybackInfo(RequestQueue queue1) throws IOException, JSONException {
        String serverUrl = "https://dev.vdocipher.com/api/videos/123e4398b73baf3c1d98bcb7597ba480/otp";

        jobj = null;
        String otp = null;
        String playbackInfo = null;

        StringRequest request = new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "response = "+ response);
                String response1 = response.toString();
                try {
                    jobj = new JSONObject(response1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "Error = "+ error);
            }
        }) {
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                   // String credentials = "username:password";
                  //  String auth = "Basic "
                          //  + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    headers.put("Accept", "application/json");
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "Apisecret w4PEvPvG74EtK7S7Mi1z0eGgDPREDcQRwa2vkoglERKJGLiClxZmStyJV2iP96bh");
                    return headers;
                }
            };
            queue1.add(request);
            while(jobj == null ){

                            }
        otp = jobj.getString("otp");
        playbackInfo = jobj.getString("playbackInfo");
        return Pair.create(otp, playbackInfo);
        //return Pair.create("20160313versASE323FfcHC51cFc9f7wWlmn5KQ245bBvthmJakmmFWAyCWeachH", "eyJ2aWRlb0lkIjoiNGNmM2Q0M2Y4YWNiOGI4ZGI3NDhhOGNmZDY3MTE5MjQifQ==");
    }
}
