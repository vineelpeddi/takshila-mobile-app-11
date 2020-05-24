package in.TakshilaLearning.TakshilaLearning.LoginAuthorization;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import in.TakshilaLearning.TakshilaLearning.MainActivity;
import in.TakshilaLearning.TakshilaLearning.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private final String clientId = "qwaaOwxSMIC7jgAuPEbjCESLoVo1oyps4uOMrN9M";
    private final String clientSecret = "qQ9qvwJlYA3XJI901eEzyuVLSSNP2WkREok3PfKS";
    private final String redirectUri = "https://www.dev.takshi.la";
    private static final String TAG = "abc";
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_login = (Button)findViewById(R.id.btn_logininfo);
        Button btn_info = (Button)findViewById(R.id.btn_info);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://dev123.takshi.la/wp-login.php?redirect_to=https%3A%2F%2Fdev123.takshi.la%2F%3Foauth%3Dauthorize%26response_type%3Dcode%26client_id%3DqwaaOwxSMIC7jgAuPEbjCESLoVo1oyps4uOMrN9M%26client_secret%3DqQ9qvwJlYA3XJI901eEzyuVLSSNP2WkREok3PfKS%26redirect_uri%3Dhttps%253A%252F%252Fwww.dev.takshi.la"));
                startActivity(intent);

            }
        });

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                /*

                Log.e(TAG, "1233 =" + token);
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://dev123.takshi.la")
                        .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.build();
                LoginRetrofit client = retrofit.create(LoginRetrofit.class);
                Call<ResponseBody> call =  client.getInfo(
                        token
                );
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.e(TAG, "1233 = " + response);

                        if(response.isSuccessful()) {
                            try {
                                Toast.makeText(Login.this, response.body().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            Toast.makeText(Login.this, "qwe", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Login.this,"no",Toast.LENGTH_SHORT).show();

                    }
                });
*/
            }
        });
        }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        Uri uri = getIntent().getData();
        if(uri != null){

            Log.e(TAG, "1233 = " + uri);
            String code = uri.getQueryParameter("code");
            Log.e(TAG, "1233 = " + code);
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://dev123.takshi.la")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            LoginRetrofit client = retrofit.create(LoginRetrofit.class);
            Call<AccessToken> accessTokenCall =  client.getAccessToken(
                    "authorization_code",
                    code,
                    clientId,
                    clientSecret,
                    redirectUri
            );
            accessTokenCall.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    Toast.makeText(Login.this,"yay",Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "123456789 = " + response);
                    token = response.body().getAccessToken();
                    String type = response.body().getTokenType();
                    String expires = response.body().getExpiresIn();
                    String scope = response.body().getScope();
                    Log.e(TAG, "1234 = " + token);
                    Log.e(TAG, "1234 = " + type);
                    Log.e(TAG, "1234 = " + expires);
                    Log.e(TAG, "1234 = " + scope);


                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(Login.this,"no",Toast.LENGTH_SHORT).show();
                }
            });


        }

         */
    }
}
