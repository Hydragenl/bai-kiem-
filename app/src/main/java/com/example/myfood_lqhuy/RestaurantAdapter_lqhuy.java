package com.example.myfood_lqhuy;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantAdapter_lqhuy extends RecyclerView.Adapter<RestaurantAdapter_lqhuy.ViewHolder> {

    private Context context;
    private List<Restaurant> restaurantList;

    public RestaurantAdapter_lqhuy(Context context, List<Restaurant> list) {
        this.context = context;
        this.restaurantList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurant_lqhuy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant res = restaurantList.get(position);
        holder.tvName.setText(res.name);
        holder.imgRes.setImageResource(R.drawable.ic_launcher_background); // ảnh mẫu

        // Khi click vào ảnh hoặc tên
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FoodListActivity_lqhuy.class);
            intent.putExtra("restaurant_id", res.id);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRes;
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRes = itemView.findViewById(R.id.imgRestaurant);
            tvName = itemView.findViewById(R.id.tvRestaurantName);
        }
    }
}
