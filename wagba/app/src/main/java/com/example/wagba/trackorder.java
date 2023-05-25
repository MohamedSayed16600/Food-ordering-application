package com.example.wagba;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wagba.ui.Favorites.Orderhis;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class trackorder extends Fragment {

    TextView orderID,orderPlaced,PreparingOrder,Deliveringyourorder,OrderArrived;
    //ImageView Iorderplaced,IPreparingOrder,IDeliveringyourorder,IOrderArrived;
    String order_ID;
    DatabaseReference orderref;
    String status;
    Button cancel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_trackorder, container, false);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        orderID=root.findViewById(R.id.OrderIdValue);

        orderPlaced=root.findViewById(R.id.orderplacedtext);
        PreparingOrder=root.findViewById(R.id.preparingordertext);
        Deliveringyourorder=root.findViewById(R.id.deliveringordertext);
        OrderArrived=root.findViewById(R.id.orderdeliveredtext);



        cancel=root.findViewById(R.id.CancelOrderButton);



        Bundle bundle=this.getArguments();
        if(bundle !=null){

            order_ID=bundle.getString("ID");
            orderref= FirebaseDatabase.getInstance().getReference("OrderHistory").child(user.getUid()).child(order_ID);
            orderID.setText(order_ID);

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    orderref.child("status").setValue("Cancelled");
                    Toast.makeText(getActivity(),"Order Cancelled Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getContext(), MainActivity.class);
                    startActivity(intent2);

                }
            });


            orderref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    status=snapshot.child("status").getValue().toString();

                    if(status.equalsIgnoreCase("In Progress")){
                        Log.d("statuso", "Progress: ");
                        orderPlaced.setTextColor(Color.parseColor("#e51c24"));
                        PreparingOrder.setTextColor(Color.parseColor("#ECECEC"));
                        Deliveringyourorder.setTextColor(Color.parseColor("#ECECEC"));
                        OrderArrived.setTextColor(Color.parseColor("#ECECEC"));

                        cancel.setEnabled(true);
                        cancel.setBackgroundColor(Color.parseColor("#e51c24"));

                    }
                    else if(status.equalsIgnoreCase("Preparing")){
                        Log.d("statuso", "Preparing: ");
                        orderPlaced.setTextColor(Color.parseColor("#ECECEC"));
                        PreparingOrder.setTextColor(Color.parseColor("#e51c24"));
                        Deliveringyourorder.setTextColor(Color.parseColor("#ECECEC"));
                        OrderArrived.setTextColor(Color.parseColor("#ECECEC"));

                        cancel.setEnabled(false);
                        cancel.setBackgroundColor(Color.parseColor("#ECECEC"));

                    }else if(status.equalsIgnoreCase("Delivering")){
                        Log.d("statuso", "Delivering: ");
                        orderPlaced.setTextColor(Color.parseColor("#ECECEC"));
                        PreparingOrder.setTextColor(Color.parseColor("#ECECEC"));
                        Deliveringyourorder.setTextColor(Color.parseColor("#e51c24"));
                        OrderArrived.setTextColor(Color.parseColor("#ECECEC"));


                        cancel.setEnabled(false);
                        cancel.setBackgroundColor(Color.parseColor("#ECECEC"));


                    }else if(status.equalsIgnoreCase("Done")){
                        Log.d("statuso", "Done: ");
                        orderPlaced.setTextColor(Color.parseColor("#ECECEC"));
                        PreparingOrder.setTextColor(Color.parseColor("#ECECEC"));
                        Deliveringyourorder.setTextColor(Color.parseColor("#ECECEC"));
                        OrderArrived.setTextColor(Color.parseColor("#e51c24"));


                        cancel.setEnabled(false);
                        cancel.setBackgroundColor(Color.parseColor("#ECECEC"));

                    }
                    else{
                        orderPlaced.setTextColor(Color.parseColor("#ECECEC"));
                        PreparingOrder.setTextColor(Color.parseColor("#ECECEC"));
                        Deliveringyourorder.setTextColor(Color.parseColor("#ECECEC"));
                        OrderArrived.setTextColor(Color.parseColor("#ECECEC"));


                        cancel.setEnabled(false);
                        cancel.setBackgroundColor(Color.parseColor("#ECECEC"));
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }




        return root;
    }
}