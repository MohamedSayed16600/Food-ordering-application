package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.wagba.models.FavoritesModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class checkout extends AppCompatActivity {
    RadioButton gate3,gate4,noon,afternoon;
    Button checkoutbutton;
    DatabaseReference orderhistoryref,Cartref;
    FavoritesModel order;
    String date;
    String Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        gate3=findViewById(R.id.radioButton2);
        gate4=findViewById(R.id.radioButton3);
        noon=findViewById(R.id.noon);
        afternoon=findViewById(R.id.threepm);
        checkoutbutton=findViewById(R.id.CheckOut);

        checkoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!gate3.isChecked() && !gate4.isChecked()){
                    Toast.makeText(checkout.this,"Please select a gate",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!noon.isChecked() && !afternoon.isChecked()){
                    Toast.makeText(checkout.this,"Please choose delivery time",Toast.LENGTH_SHORT).show();
                    return;
                }

                String uniqueID = UUID.randomUUID().toString().replace("-","").substring(0,8);
                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat currentDate=new SimpleDateFormat("dd/MM/yyyy");
                date=currentDate.format(calendar.getTime());
                SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
                Time=currentTime.format(calendar.getTime());
                Log.d("oufaa", Time);

                order=new FavoritesModel();
                order.setOrderID("order_"+uniqueID);
                if(noon.isChecked()){
                    order.setTime("12 PM");
                }
                else{
                    order.setTime("3 PM");
                }
                if(gate3.isChecked()){
                    order.setGate("Gate 3");
                }else{
                    order.setGate("Gate 4");
                }
                order.setStatus("In Progress");

                Intent intent=getIntent();
                order.setTotalPrice(String.valueOf(intent.getIntExtra("Price",-1)));

                order.setDate(date);


                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                orderhistoryref= FirebaseDatabase.getInstance().getReference().child("OrderHistory").child(user.getUid()).child(uniqueID);
                orderhistoryref.setValue(order);
                Cartref= FirebaseDatabase.getInstance().getReference().child("Cart").child(user.getUid());
                Cartref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot msnap: snapshot.getChildren()){
                            //Log.d("leleel", getRef(position).getKey());
                            msnap.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Toast.makeText(checkout.this,"Order Placed Successfully",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                finish();

            }
        });



    }
}
