package in.TakshilaLearning.TakshilaLearning.LoginAuthorization;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CoursesForSaleFragment;
import in.TakshilaLearning.TakshilaLearning.R;

public class LoginSignUpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_signup, container , false) ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_login_view_courses = (Button)view.findViewById(R.id.btn_login_view_your_courses);
        Button btn_look_for_courses = (Button)view.findViewById(R.id.btn_look_for_courses);
        btn_login_view_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedFragment = new LoginFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_main_container,selectedFragment).addToBackStack(null).commit();
            }
        });

        btn_look_for_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedFragment = new CoursesForSaleFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_main_container,selectedFragment).addToBackStack(null).commit();
            }
        });
    }
}
