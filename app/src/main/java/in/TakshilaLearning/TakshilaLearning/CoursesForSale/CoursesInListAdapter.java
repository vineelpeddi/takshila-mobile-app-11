package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.TakshilaLearning.TakshilaLearning.R;

import static cz.msebera.android.httpclient.client.methods.RequestBuilder.delete;
import static in.TakshilaLearning.TakshilaLearning.CoursesForSale.Cart.*;

public class CoursesInListAdapter extends RecyclerView.Adapter<CoursesInListAdapter.CoursesInListViewHolder> {
    private ArrayList<RvCourseForSaleContentPojo> mdata= new ArrayList<>();
    private static Context context;
    RvCourseForSaleContentPojo myListDat2 = new RvCourseForSaleContentPojo() ;

    public CoursesInListAdapter(Context context){
        this.context = context;

    }
    @NonNull
    @Override
    public CoursesInListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_courses_in_cart, parent, false);
        return new CoursesInListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesInListViewHolder holder, int position) {

        holder.course_title.setText(myListDat2.getName());
        holder.price_cart.setText(myListDat2.getPrice());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                      deleteFromCart(myListDat2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class CoursesInListViewHolder extends RecyclerView.ViewHolder{
        TextView course_title , price_cart ;
        ImageView delete;
        public CoursesInListViewHolder(View itemView){
          super(itemView);
          course_title = itemView.findViewById(R.id.tv_course_title);
          price_cart   = itemView.findViewById(R.id.tv_price_cart);
          delete       = itemView.findViewById(R.id.iv_delete);
        }


    }
}
