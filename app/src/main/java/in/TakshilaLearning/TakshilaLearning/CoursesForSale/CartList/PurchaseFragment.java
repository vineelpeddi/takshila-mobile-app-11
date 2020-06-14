package in.TakshilaLearning.TakshilaLearning.CoursesForSale.CartList;


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

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CartList.Cart;
import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CartList.CartListAdapter;
import in.TakshilaLearning.TakshilaLearning.CoursesForSale.RvCourseForSaleContentPojo;
import in.TakshilaLearning.TakshilaLearning.R;

public class PurchaseFragment extends Fragment {
    RecyclerView rv_cart_list;
    ArrayList<RvCourseForSaleContentPojo> CartListData;
    static CartListAdapter adapter;

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
        Cart c1 = new Cart();
        CartListData = c1.getCartProducts();
        rv_cart_list = (RecyclerView)view.findViewById(R.id.rv_cart_list);
        adapter = new CartListAdapter(getActivity());
        adapter.setListContent(CartListData);
        for(int i=0;i<CartListData.size();i++){
            System.out.println(CartListData.get(i).getName());
        }
        rv_cart_list.setAdapter(adapter);
        rv_cart_list.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

}
