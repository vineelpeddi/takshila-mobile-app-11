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

public class CourseForSaleDescriptionFragment extends Fragment {
    String description, price;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_for_sale_description, container,false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            description = bundle.getString("description");
            price = bundle.getString("price");
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_course_description = (TextView)view.findViewById(R.id.tv_course_for_sale_description);
        String plainTextDescription = Html.fromHtml(description).toString();
        tv_course_description.setText(plainTextDescription + "Price: " + price);
    }
}
