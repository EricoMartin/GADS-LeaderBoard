package com.example.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class LearnersAdapter extends RecyclerView.Adapter<LearnersAdapter.LearnerAdapterVH> {
    private List<LearnerResponse> learnerResponseList;
    private Context context;

    public LearnersAdapter(){

    }
    public void setData(List<LearnerResponse> learnerResponseList){
        this.learnerResponseList = learnerResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LearnerAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new LearnerAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_learner, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerAdapterVH holder, int position) {
        LearnerResponse learnerResponse = learnerResponseList.get(position);

        String name = learnerResponse.getName();
        String image = learnerResponse.getBadgeUrl();
        int hours = learnerResponse.getHours();
        String country = learnerResponse.getCountry();

        String full_text = name + '\n' + hours + " learning hours," + country;
        holder.textView.setText(full_text );
        if(image != null){
            Picasso.get()
                    .load(learnerResponse.getBadgeUrl())
                    .centerCrop()
                    .fit().into(holder.imageView);
        }

    }


    @Override
    public int getItemCount() {
        return learnerResponseList.size();
    }

    public class LearnerAdapterVH extends RecyclerView.ViewHolder {


        TextView textView;
        ImageView imageView;


        public LearnerAdapterVH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_made);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}

