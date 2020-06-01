package in.TakshilaLearning.TakshilaLearning.SearchCoursesList;

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

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CoursesForSaleFragment;
import in.TakshilaLearning.TakshilaLearning.LoginAuthorization.model.AccessToken;
import in.TakshilaLearning.TakshilaLearning.R;
import in.TakshilaLearning.TakshilaLearning.singleTonExample;

import static com.google.android.exoplayer2.ExoPlayerLibraryInfo.TAG;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search , container , false) ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText et_search_for_courses = (EditText)view.findViewById(R.id.et_search_for_courses);

        Button btn_search = (Button)view.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String search = et_search_for_courses.getText().toString();
                bundle.putString("search",search);

                Fragment selectedFragment = new SearchResultFragment();
                selectedFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_main_container,selectedFragment).commit();
            }
        });



    }
}
