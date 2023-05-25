package com.example.wagba.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;
import com.example.wagba.models.Cartmodel;



import java.util.List;

public class Cartadaptor extends RecyclerView.Adapter<Cartadaptor.ViewHolder> {

    List<Cartmodel> list;
    TextView quanfield;
    int quantity;
    Button addquan;

    public Cartadaptor(List<Cartmodel> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cart_img);
            name=itemView.findViewById(R.id.cart_name);
            price=itemView.findViewById(R.id.cart_price);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Cartadaptor.ViewHolder holder, int position) {

       // holder.imageView.setImageResource(list.get(position).getImageurlCart());
        holder.name.setText(list.get(position).getNameCart());
        holder.price.setText(list.get(position).getPriceCart());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
