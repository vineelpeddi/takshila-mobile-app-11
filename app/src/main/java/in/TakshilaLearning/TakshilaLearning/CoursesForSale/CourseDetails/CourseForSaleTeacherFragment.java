package in.TakshilaLearning.TakshilaLearning.CoursesForSale.CourseDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.TakshilaLearning.TakshilaLearning.R;

public class CourseForSaleTeacherFragment extends Fragment {
    String teacher;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_course_for_sale_teacher, container,false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            teacher = bundle.getString("teacher");
        }
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_course_teacher = (TextView)view.findViewById(R.id.tv_course_for_sale_teacher);
        tv_course_teacher.setText(teacher);
    }
}
