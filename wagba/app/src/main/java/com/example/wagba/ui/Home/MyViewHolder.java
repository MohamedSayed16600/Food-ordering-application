package com.example.wagba.ui.Home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;


public class MyViewHolder extends RecyclerView.ViewHolder {

ImageView imageView;
TextView textView;
CardView cardView;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.hor_img);
        textView=itemView.findViewById(R.id.hor_text);
        cardView=itemView.findViewById(R.id.cardview);
    }
}
