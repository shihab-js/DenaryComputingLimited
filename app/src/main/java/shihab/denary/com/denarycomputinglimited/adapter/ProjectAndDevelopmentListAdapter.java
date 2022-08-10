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

public class ProjectAndDevelopmentListAdapter extends RecyclerView.Adapter<ProjectAndDevelopmentListAdapter.ViewHolder> {


    private final Context context;

    private final ArrayList<String> titleList;
    private final ArrayList<String> shortDescription;
    private final ArrayList<String> longDescription;
    private final ArrayList<String> iconUrl;

    public ProjectAndDevelopmentListAdapter(Context context, ArrayList<String> titleList, ArrayList<String> shortDescription, ArrayList<String> longDescription, ArrayList<String> iconUrl) {
        this.context = context;
        this.titleList = titleList;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.iconUrl = iconUrl;
    }
    @Override
    public ProjectAndDevelopmentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.development_and_project_card_row,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectAndDevelopmentListAdapter.ViewHolder holder, int position) {

        holder.titleText.setText(titleList.get(position));
        holder.shortText.setText(shortDescription.get(position));
        holder.longText.setText(longDescription.get(position));

        Glide.with(context)
                .load(iconUrl.get(position))
                .placeholder(R.drawable.courses)
                .into(holder.dev_icon);

    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView titleText;
            private TextView shortText;
            private TextView longText;
            private ImageView dev_icon;

        public ViewHolder(View itemView) {
            super(itemView);

             titleText = itemView.findViewById(R.id.title);
             shortText = itemView.findViewById(R.id.shortDescription);
             longText = itemView.findViewById(R.id.longDescription);
             dev_icon = itemView.findViewById(R.id.icon);


        }
    }
}
