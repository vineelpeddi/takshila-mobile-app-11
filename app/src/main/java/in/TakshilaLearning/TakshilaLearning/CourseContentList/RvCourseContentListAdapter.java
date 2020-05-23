package in.TakshilaLearning.TakshilaLearning.CourseContentList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import in.TakshilaLearning.TakshilaLearning.CourseVideo.PlayerActivity;
import in.TakshilaLearning.TakshilaLearning.R;

public class RvCourseContentListAdapter extends RecyclerView.Adapter<RvCourseContentListAdapter.ViewHolder> {
    private ArrayList<RvCourseContentPojo> CourseContentList = new ArrayList<>();
    private final LayoutInflater inflator;

    private static Context context;



    // RecyclerView recyclerView;
    public RvCourseContentListAdapter(Context context) {
        this.context = context;
        inflator = LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.rv_course_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RvCourseContentPojo myListData = CourseContentList.get(position);
        holder.tv_content.setText(myListData.getTitle());

    }

    public void setListContent(ArrayList<RvCourseContentPojo> list_members){
        this.CourseContentList = list_members;
        notifyItemRangeChanged(0,list_members.size());

    }

    @Override
    public int getItemCount() {
        return CourseContentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "abc";
        public TextView tv_content;
        public Button btn_content;
        public ViewHolder(View itemView) {
            super(itemView);

            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            this.btn_content = (Button)itemView.findViewById(R.id.btn_content);

            btn_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "1233 = ");
                    Intent intent = new Intent(itemView.getContext(), PlayerActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
