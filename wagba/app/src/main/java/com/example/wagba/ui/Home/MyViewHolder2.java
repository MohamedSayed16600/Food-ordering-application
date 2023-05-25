package com.example.wagba.ui.Home;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;

public class MyViewHolder2 extends RecyclerView.ViewHolder {

    ImageView imageView2;
    TextView textView1,textView2,textView3;
    Button addcart;

    public MyViewHolder2(@NonNull View itemView) {
        super(itemView);

        imageView2=itemView.findViewById(R.id.ver_img);
        textView1=itemView.findViewById(R.id.name);
        textView2=itemView.findViewById(R.id.price);
        textView3=itemView.findViewById(R.id.timing);
        addcart=itemView.findViewById(R.id.add_to_cart);
    }
}
