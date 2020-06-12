package in.TakshilaLearning.TakshilaLearning.CoursesForSale;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import in.TakshilaLearning.TakshilaLearning.R;

public class PurchaseFragment extends Fragment {
    RecyclerView rv_cart_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_purchase , container , false);




        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //  getCourseinfo(token);
        rv_cart_list = (RecyclerView)view.findViewById(R.id.rv_cart_list);
        rv_cart_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_cart_list.setAdapter(new CoursesInListAdapter(getActivity()));

    }
}
