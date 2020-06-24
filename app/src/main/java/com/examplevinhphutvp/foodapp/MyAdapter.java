package com.examplevinhphutvp.foodapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    private Context mContext;
    private List<FoodData> myFoodList;

    public MyAdapter(Context mContext, List<FoodData> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_item,parent,false);

        return new  FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder, int position) {

        Glide.with(mContext)
                .load(myFoodList.get(position).getImage())
                .into(holder.imageView);
       // holder.imageView.setImageResource(myFoodList.get(position).getImage());
        holder.mTitle.setText(myFoodList.get(position).getName());
        holder.mDescription.setText(myFoodList.get(position).getDescription());
        holder.mPrice.setText(myFoodList.get(position).getPrice());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (mContext,DetailActivity.class);
                intent.putExtra("Image",myFoodList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Description",myFoodList.get(holder.getAdapterPosition()).getDescription());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }

    public void filteredList(ArrayList<FoodData> filterlist) {
        myFoodList = filterlist;
        notifyDataSetChanged();
    }
}

class FoodViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView mTitle,mDescription,mPrice;
    CardView mCardView;

    public FoodViewHolder( View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ImgView);
        mCardView = itemView.findViewById(R.id.myCardView);
        mPrice = itemView.findViewById(R.id.tvtPrice);
        mDescription = itemView.findViewById(R.id.tvtDesription);
        mTitle = itemView.findViewById(R.id.tvtTitle);

    }
}
