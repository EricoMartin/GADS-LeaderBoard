package com.example.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gadsleaderboard.ItemFragment.OnListFragmentInteractionListener;
import com.example.gadsleaderboard.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter2 extends RecyclerView.Adapter<MyItemRecyclerViewAdapter2.LearnerAdapterVH> {
    private List<LearnerResponse2> learnerResponseList = new ArrayList<>();
    private Context context;

    public MyItemRecyclerViewAdapter2(){

    }
    public void setData(List<LearnerResponse2> learnerResponseList){
        this.learnerResponseList = learnerResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LearnerAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new LearnerAdapterVH(LayoutInflater.from(context).inflate(R.layout.fragment_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemRecyclerViewAdapter2.LearnerAdapterVH holder, int position) {
        LearnerResponse2 learnerResponse = learnerResponseList.get(position);

        String name = learnerResponse.getName();
        String image = learnerResponse.getBadgeUrl();
        int score = learnerResponse.getScore();
        String country = learnerResponse.getCountry();

        String full_text = name + '\n' + score + " Skill IQ score, " + country;
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
        try{
            return learnerResponseList.size();
        }catch (Exception e){
            Log.e(String.valueOf(learnerResponseList), "Raised an exception during getItemCount Method", e);
        }
        return learnerResponseList.size();
    }

    public class LearnerAdapterVH extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textView;
        ImageView imageView;


        public LearnerAdapterVH(@NonNull View itemView) {
            super(itemView);
            cardView =itemView.findViewById(R.id.cardView);
            textView = itemView.findViewById(R.id.text_made);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
