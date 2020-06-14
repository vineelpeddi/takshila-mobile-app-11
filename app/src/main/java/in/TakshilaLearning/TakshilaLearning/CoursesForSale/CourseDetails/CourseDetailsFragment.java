package in.TakshilaLearning.TakshilaLearning.CoursesForSale.CourseDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CartList.Cart;
import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CartList.PurchaseFragment;
import in.TakshilaLearning.TakshilaLearning.CoursesForSale.RvCourseForSaleContentPojo;
import in.TakshilaLearning.TakshilaLearning.R;

import static com.android.volley.VolleyLog.TAG;

public class CourseDetailsFragment extends Fragment {
    String description,teacher,price,name,id;

    Button btn_desc;
    Button btn_teacher_info;
    Button btn_material;
    Button btn_purchase;
    RvCourseForSaleContentPojo Coursedetailobj;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View v = inflater.inflate(R.layout.fragment_course_for_sale_details, container , false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            description = bundle.getString("description");
            name = bundle.getString("name");
            id = bundle.getString("id");
            teacher = bundle.getString("teacher");
            price = bundle.getString("price");
        }
        Coursedetailobj = new RvCourseForSaleContentPojo();
        Coursedetailobj.setId(id);
        Coursedetailobj.setName(name);
        Coursedetailobj.setDescription(description);
        Coursedetailobj.setPrice(price);
        Coursedetailobj.setTeacher(teacher);
        Log.e(TAG, "n " + name);
        Log.e(TAG, "t " + teacher);
        Log.e(TAG, "p " + price);

        btn_desc = v.findViewById(R.id.btn_desc);
        btn_teacher_info = v.findViewById(R.id.btn_teacher_info);
        btn_material = v.findViewById(R.id.btn_material);
        btn_purchase = v.findViewById(R.id.btn_purchase);
        Fragment  childfragmentDescription = new CourseForSaleDescriptionFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("description",description);
        bundle1.putString("price",price);
        childfragmentDescription.setArguments(bundle1);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_child, childfragmentDescription);
        transaction.commit();


     return  v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment  childfragmentDescription = new CourseForSaleDescriptionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("description",description);
                bundle.putString("price",price);
                childfragmentDescription.setArguments(bundle);
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_child, childfragmentDescription);
                transaction.commit();
            }
        });

        btn_teacher_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment  childfragmentTeacher = new CourseForSaleTeacherFragment();
                Bundle bundle = new Bundle();
                bundle.putString("teacher",teacher);
                childfragmentTeacher.setArguments(bundle);

                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_child, childfragmentTeacher);
                transaction.commit();

            }
        });

        btn_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment  childfragmentMaterial = new CourseForSaleMaterialFragment();

                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_child, childfragmentMaterial);
                transaction.commit();

            }
        });

        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cart.addingToCartList(Coursedetailobj);
                Fragment selectedFragment = new PurchaseFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_main_container,selectedFragment).addToBackStack(null).commit();


            }
        });

    }
}
