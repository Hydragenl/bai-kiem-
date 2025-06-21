package com.example.myfood_lqhuy;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter_lqhuy extends RecyclerView.Adapter<FoodAdapter_lqhuy.ViewHolder> {

    private Context context;
    private List<Food> foodList;

    public FoodAdapter_lqhuy(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodAdapter_lqhuy.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food_lqhuy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter_lqhuy.ViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.tvName.setText(food.name);
        holder.tvPrice.setText(String.format("%.2f VND", food.price));
        holder.img.setImageResource(R.drawable.ic_launcher_background); // ảnh mẫu

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FoodDetailActivity_lqhuy.class);
            intent.putExtra("food_id", food.id);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgFood);
            tvName = itemView.findViewById(R.id.tvFoodName);
            tvPrice = itemView.findViewById(R.id.tvFoodPrice);
        }
    }
}
