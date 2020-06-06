package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import in.TakshilaLearning.TakshilaLearning.R;

public class CoursesForSaleListAdapter extends RecyclerView.Adapter<CoursesForSaleListAdapter.CoursesViewHolder>{
    ArrayList<String> edata ;
    public CoursesForSaleListAdapter( ArrayList data ){
        edata = data;
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

        holder.tv_course_name.setText(edata.get(position));

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
              btn_about      = itemView.findViewById(R.id.btn_add);
          }
      }
}
