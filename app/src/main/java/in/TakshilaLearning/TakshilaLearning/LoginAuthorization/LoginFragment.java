package in.TakshilaLearning.TakshilaLearning.LoginAuthorization;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CoursesForSaleFragment;
import in.TakshilaLearning.TakshilaLearning.LoginAuthorization.model.AccessToken;
import in.TakshilaLearning.TakshilaLearning.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {
    private final String clientId = "I2h85mWFdB8x9YSXlEEh7wIcmvrIbTaq0Tz8loA4";
    private final String clientSecret = "wClOcbnIlcmw8z7miCTP5IKtCprD1DI4oV79QfeC";
    private static final String TAG = "abc";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_login, container,false);

        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText et_usrname = (EditText)view.findViewById(R.id.et_username);
        EditText et_pswd = (EditText)view.findViewById(R.id.et_pswd);
        Button btn_login = (Button)view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname,pswd;
                usrname = et_usrname.getText().toString();
                pswd = et_pswd.getText().toString();

                AccessToken user = new AccessToken("password",usrname,pswd,clientId,clientSecret);
                sendNetworkRequest(user);




            }
        });

    }
    private void sendNetworkRequest(AccessToken user){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://dev123.takshi.la")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        LoginRetrofit client = retrofit.create(LoginRetrofit.class);
        Call<AccessToken> accessTokenCall =  client.getAccessToken(user);
        accessTokenCall.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                Log.e(TAG, "123456789 = " + response);

                String token = response.body().getAccesstoken();
                String type = response.body().getTokenType();
                String expires = response.body().getExpiresIn();
                String scope = response.body().getScope();
                Log.e(TAG, "1234 = " + token);
                Log.e(TAG, "1234 = " + type);
                Log.e(TAG, "1234 = " + expires);
                Log.e(TAG, "1234 = " + scope);
                Bundle bundle = new Bundle();
                bundle.putString("access_token",token);
                Fragment selectedFragment = new CoursesForSaleFragment();
                selectedFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_main_container,selectedFragment).commit();



            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.e(TAG, "123456789 = " + "error");

            }
        });

    }




}
