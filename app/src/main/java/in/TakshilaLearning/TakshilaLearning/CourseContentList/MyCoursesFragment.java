package in.TakshilaLearning.TakshilaLearning.CourseContentList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CoursesForSaleFragment;
import in.TakshilaLearning.TakshilaLearning.LoginAuthorization.LoginFragment;
import in.TakshilaLearning.TakshilaLearning.R;

public class MyCoursesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_courses, container, false);


        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_add_more_courses = (Button)view.findViewById(R.id.btn_add_more_courses);
        Button btn_search_your_courses = (Button)view.findViewById(R.id.btn_search_your_courses);




        btn_search_your_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedFragment = new CourseContentFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_main_container,selectedFragment).commit();
            }
        });


    }
}
