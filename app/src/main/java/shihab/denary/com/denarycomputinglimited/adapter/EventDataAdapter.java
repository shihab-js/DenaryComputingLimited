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

public class EventDataAdapter extends RecyclerView.Adapter<EventDataAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<String> event_name;
    private final ArrayList<String> speaker_name;
    private final ArrayList<String> event_date;
    private final ArrayList<String> event_time;
    private final ArrayList<String> imageUrl;
    private final ArrayList<String> location;


    public EventDataAdapter(Context context, ArrayList<String> event_name,ArrayList<String> speaker_name,ArrayList<String> event_date,
                            ArrayList<String> event_time,ArrayList<String> location,ArrayList<String> imageUrl) {
        this.context=context;
        this.event_name=event_name;
        this.speaker_name = speaker_name;
        this.event_date = event_date;
        this.event_time = event_time;
        this.imageUrl=imageUrl;
        this.location=location;

    }

    @Override
    public EventDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventDataAdapter.ViewHolder holder, int position) {


        holder.titleText.setText(event_name.get(position));
        holder.speaker.setText(speaker_name.get(position));
        holder.date.setText(event_date.get(position));
        holder.time.setText(event_time.get(position));
        holder.local.setText(location.get(position));

        Glide.with(context)
                .load(imageUrl.get(position))
                .placeholder(R.drawable.courses)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return event_name.size();
    }

    public class ViewHolder extends RecyclerView .ViewHolder{

                TextView titleText;
                TextView speaker;
                TextView date;
                TextView time;
                TextView local;
                ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.title);
            speaker =  itemView.findViewById(R.id.speaker);
            date =  itemView.findViewById(R.id.date);
            time =  itemView.findViewById(R.id.time);
            local =  itemView.findViewById(R.id.location);
            imageView = itemView.findViewById(R.id.icon);

        }
    }
}
