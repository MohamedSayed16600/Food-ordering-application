package com.example.wagba.ui.Home;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;

public class Myviewholdercart extends RecyclerView.ViewHolder{

    public ImageView imagecart;
    public TextView nameCart;
    public TextView pricecart;
    public Button addquan;
    public Button minquan;
    public Button delete;
    public TextView quan;



    public Myviewholdercart(@NonNull View itemView) {
        super(itemView);
        imagecart=itemView.findViewById(R.id.cart_img);
        nameCart=itemView.findViewById(R.id.cart_name);
        pricecart=itemView.findViewById(R.id.cart_price);

        addquan=itemView.findViewById(R.id.addqtn);
        minquan=itemView.findViewById(R.id.minusqtn);
        delete=itemView.findViewById(R.id.delete_cart);
        quan=itemView.findViewById(R.id.quantityfield);



    }
}
