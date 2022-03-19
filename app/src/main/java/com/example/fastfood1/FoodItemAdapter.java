package com.example.fastfood1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder>
{

    public FoodItemAdapter(Context context, ArrayList<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    Context context;
    ArrayList<FoodItem> foodItems;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemAdapter.ViewHolder holder, int position) {
        FoodItem item= foodItems.get(position);
        holder.foodName.setText(item.getFoodName());
        holder.price.setText(item.getPrice());
        Glide.with(context).load(item.getFoodImg()).into(holder.foodImg);
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodName;
        TextView price;
        ImageView foodImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName=itemView.findViewById(R.id.foodName);
            price=itemView.findViewById(R.id.price);
            foodImg=itemView.findViewById(R.id.foodImg);
        }
    }
}
