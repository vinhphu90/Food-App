package com.examplevinhphutvp.foodapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.FoodViewHolder> {

    private Context mContext;
    private List<FoodData> myFoodList;
    private int lastPosition = -1;

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



        setAnimation(holder.itemView,position);

    }
    public void setAnimation(View view,int position){
        if (position > lastPosition) {
            ScaleAnimation animation = new  ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1500);
            view.startAnimation(animation);
            lastPosition = position;
        }


    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }

    public void filteredList(ArrayList<FoodData> filterlist) {
        myFoodList = filterlist;
        notifyDataSetChanged();
    }
    public class FoodViewHolder extends RecyclerView.ViewHolder{
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
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (mContext,DetailActivity.class);
                    intent.putExtra("Image",myFoodList.get(getAdapterPosition()).getImage());
                    intent.putExtra("Description",myFoodList.get(getAdapterPosition()).getDescription());
                    intent.putExtra("foodName",myFoodList.get(getAdapterPosition()).getName());
                    intent.putExtra("KeyValue",myFoodList.get(getAdapterPosition()).getKey());
                    intent.putExtra("price",myFoodList.get(getAdapterPosition()).getPrice());
                    intent.putExtra("Address",myFoodList.get(getAdapterPosition()).getAddress());
                    intent.putExtra("Phone",myFoodList.get(getAdapterPosition()).getPhone());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}


