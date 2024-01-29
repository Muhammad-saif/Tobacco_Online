package com.example.mysignupapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysignupapp.R;
import com.example.mysignupapp.models.RecommendedModel;

import java.util.List;

public class RecommendedAdapter  extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {


    Context context;
    List<RecommendedModel> recommendedModelList;

    public RecommendedAdapter(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    public RecommendedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(recommendedModelList.get(position).getImg_url()).into(holder.recImg);
        holder.name.setText(recommendedModelList.get(position).getName());
        holder.description.setText(recommendedModelList.get(position).getDescription());
        holder.rating.setText(recommendedModelList.get(position).getRating());


    }

    @Override
    public int getItemCount() {
        return recommendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recImg;
        TextView name, rating, description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recImg = itemView.findViewById(R.id.rec_img);
            name = itemView.findViewById(R.id.rec_name);
            description = itemView.findViewById(R.id.rec_des);
            rating = itemView.findViewById(R.id.rec_rating);

        }
    }
}
