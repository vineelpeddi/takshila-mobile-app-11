package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.CourseDetails.CourseDetailsFragment;
import in.TakshilaLearning.TakshilaLearning.R;

import static com.android.volley.VolleyLog.TAG;

public class CoursesForSaleListAdapter extends RecyclerView.Adapter<CoursesForSaleListAdapter.CoursesViewHolder>{
    private ArrayList<RvCourseForSaleContentPojo> edata = new ArrayList<>();
    private static Context context;
    private final LayoutInflater inflator;


    public CoursesForSaleListAdapter(Context context) {
        this.context = context;
        inflator = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_courses_for_sale_layout, parent, false);
        return new CoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position ) {
        RvCourseForSaleContentPojo myListData = edata.get(position);
        holder.tv_course_name.setText(myListData.getName());
        holder.btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new CourseDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("description",myListData.getDescription());
                bundle.putString("name",myListData.getName());
                bundle.putString("id",myListData.getId());
                bundle.putString("price",myListData.getPrice());
                bundle.putString("teacher",myListData.getTeacher());
                Log.e(TAG, "n " + myListData.getName());
                Log.e(TAG, "t " + myListData.getTeacher());
                Log.e(TAG, "p " + position);
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main_container, myFragment).addToBackStack(null).commit();
            }
        });

    }

    public void setListContent(ArrayList<RvCourseForSaleContentPojo> list_members){
        this.edata = list_members;
        notifyItemRangeChanged(0,list_members.size());
    }
    @Override
    public int getItemCount() {
        return  edata.size();
    }

    public class CoursesViewHolder extends RecyclerView.ViewHolder{
          TextView tv_course_name;
          Button  btn_course , btn_add , btn_about ;

          public CoursesViewHolder (View itemView){
              super(itemView);
              tv_course_name = itemView.findViewById(R.id.tv_course_name);
              btn_course     = itemView.findViewById(R.id.btn_course);
              btn_add        = itemView.findViewById(R.id.btn_add);
              btn_about      = itemView.findViewById(R.id.btn_about);

          }
      }
}
