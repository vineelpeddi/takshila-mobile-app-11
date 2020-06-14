package in.TakshilaLearning.TakshilaLearning.CoursesForSale.CartList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.RvCourseForSaleContentPojo;
import in.TakshilaLearning.TakshilaLearning.R;

import static com.android.volley.VolleyLog.TAG;
import static in.TakshilaLearning.TakshilaLearning.CoursesForSale.CartList.Cart.*;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CoursesInListViewHolder> {
    private ArrayList<RvCourseForSaleContentPojo> mdata= new ArrayList<>();
    private static Context context;
    RvCourseForSaleContentPojo myListDat2 = new RvCourseForSaleContentPojo() ;

    public CartListAdapter(Context context){
        this.context = context;

    }
    @NonNull
    @Override
    public CoursesInListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_list_courses_in_cart, parent, false);
        return new CoursesInListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesInListViewHolder holder, int position) {
        RvCourseForSaleContentPojo myListData = mdata.get(position);

        Log.e(TAG, "xyz " + myListData.getName());

        holder.course_title.setText(myListData.getName());
        holder.price_cart.setText(myListData.getPrice());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFromCart(myListData);
                for(int i=0;i<mdata.size();i++){
                    System.out.println(mdata.get(i).getName());
                }
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new PurchaseFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main_container, myFragment).addToBackStack(null).commit();
            }
        });
    }
    public void setListContent(ArrayList<RvCourseForSaleContentPojo> list_members){
        this.mdata = list_members;
        notifyItemRangeChanged(0,list_members.size());
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
