package com.example.wagba.ui.Home;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;

public class Myviewholder3orderhis extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView status;
    public TextView orderID;
    public TextView gate;
    public TextView date;
    public TextView time;


    public Myviewholder3orderhis(@NonNull View itemView) {
        super(itemView);

        status=itemView.findViewById(R.id.orderhistorystatus);
        orderID=itemView.findViewById(R.id.order_id);
        gate=itemView.findViewById(R.id.textViewgate);
        date=itemView.findViewById(R.id.day);
        time=itemView.findViewById(R.id.Date);
        cardView=itemView.findViewById(R.id.ordercardview);
    }
}
