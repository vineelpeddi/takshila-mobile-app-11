package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import in.TakshilaLearning.TakshilaLearning.R;

public class CoursesForSaleListAdapter extends RecyclerView.Adapter<CoursesForSaleListAdapter.CoursesViewHolder>{
    private String [] data;
    public CoursesForSaleListAdapter(String [] data ){
        this.data = data;
    }


    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_courses_for_sale_layout, parent, false);
        return new CoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
        String title = data[position];
        holder.tv_course_name.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
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
