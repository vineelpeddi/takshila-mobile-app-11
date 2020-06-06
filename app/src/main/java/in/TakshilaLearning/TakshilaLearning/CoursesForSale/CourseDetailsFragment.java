package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import in.TakshilaLearning.TakshilaLearning.R;

public class CourseDetailsFragment extends Fragment {

    Button b1;
    Button b2;
    Button b3;
    Button b4;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View v = inflater.inflate(R.layout.fragment_course_details, container , false);

     b1 = v.findViewById(R.id.btn_desc);
     b2 = v.findViewById(R.id.btn_teacher_info);
     b3 = v.findViewById(R.id.btn_material);
     b4 = v.findViewById(R.id.btn_purchase);

     return  v;
    }
}
