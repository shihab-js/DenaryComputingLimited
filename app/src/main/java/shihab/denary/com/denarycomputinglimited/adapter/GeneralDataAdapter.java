package shihab.denary.com.denarycomputinglimited.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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

public class GeneralDataAdapter extends RecyclerView.Adapter<GeneralDataAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<String> maintitle;
    private final ArrayList<String> imgUrl;


    public GeneralDataAdapter(Context context,ArrayList<String> maintitle,ArrayList<String> imgUrl){
        this.context = context;
        this.maintitle = maintitle;
        this.imgUrl = imgUrl;

    }

    @Override
    public GeneralDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_card_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.textView.setText(maintitle.get(position));

        Glide.with(context)
                .load(imgUrl.get(position))
                .placeholder(R.drawable.courses)
                .into(holder.imageView);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return maintitle.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        CardView cv;

        public ViewHolder(final View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.card);
            imageView = itemView.findViewById(R.id.icon);
            textView = itemView.findViewById(R.id.title);

        }
    }
}
