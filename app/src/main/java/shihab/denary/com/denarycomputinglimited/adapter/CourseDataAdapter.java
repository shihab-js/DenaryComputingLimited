package shihab.denary.com.denarycomputinglimited.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import shihab.denary.com.denarycomputinglimited.R;

/**
 * Created by SHIHAB on 1/18/2018.
 */

public class CourseDataAdapter extends RecyclerView.Adapter<CourseDataAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<String> course_title;
    private final ArrayList<String> instructor_name;
    private final ArrayList<String> course_duration;
    private final ArrayList<String> imgUrl;


    public CourseDataAdapter(Context context, ArrayList<String> course_title, ArrayList<String> instructor_name,
                             ArrayList<String> course_duration, ArrayList<String> imgUrl){

        this.context = context;
        this.course_title = course_title;
        this.instructor_name = instructor_name;
        this.course_duration = course_duration;
        this.imgUrl = imgUrl;

    }



    @Override
    public CourseDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card_row, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseDataAdapter.ViewHolder holder, int position) {

        holder.titleText.setText(course_title.get(position));
        holder.ins_Text.setText(instructor_name.get(position));
        holder.duration.setText(course_duration.get(position));
        Glide.with(context)
                .load(imgUrl.get(position))
                .placeholder(R.drawable.courses)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return course_title.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleText;
        private TextView ins_Text;
        private TextView duration;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.icon);
            titleText = itemView.findViewById(R.id.title);
            ins_Text = itemView.findViewById(R.id.ins_name);
            duration = itemView.findViewById(R.id.duration);


        }
    }
}
