package com.example.wagbaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText ID,Status;
    String OrderId,OrderStatus,number;
    Button button;
   DatabaseReference order,user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ID=findViewById(R.id.editText);
        Status=findViewById(R.id.Status);
        button=findViewById(R.id.button);

       order= FirebaseDatabase.getInstance().getReference().child("OrderHistory");
        user=FirebaseDatabase.getInstance().getReference().child("Users");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderId=ID.getText().toString();
                number=Status.getText().toString();
                Log.d("Omar", order.getKey());
                Log.d("Omar", user.getKey());

                order.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //available for user omar only
                        for(DataSnapshot msnap:snapshot.getChildren()){
                            Log.d("omarlotfy", msnap.getKey());
                            if(msnap.child(OrderId).exists()){
                                //Log.d("omarlotfy","entered here");

                                if(number.equalsIgnoreCase("1")){
                                    Toast.makeText(MainActivity.this,"Order Status successfully changed",Toast.LENGTH_SHORT).show();
                                    OrderStatus="Preparing";
                                    order.child(msnap.getKey()).child(OrderId).child("status").setValue(OrderStatus);
                                    ID.getText().clear();
                                    Status.getText().clear();

                                }else if(number.equalsIgnoreCase("2")){
                                    Toast.makeText(MainActivity.this,"Order Status successfully changed",Toast.LENGTH_SHORT).show();
                                    OrderStatus="Delivering";
                                    order.child(msnap.getKey()).child(OrderId).child("status").setValue(OrderStatus);
                                    ID.getText().clear();
                                    Status.getText().clear();

                                }else if(number.equalsIgnoreCase("3")){
                                    Toast.makeText(MainActivity.this,"Order Status successfully changed",Toast.LENGTH_SHORT).show();
                                    OrderStatus="Done";
                                    order.child(msnap.getKey()).child(OrderId).child("status").setValue(OrderStatus);
                                    ID.getText().clear();
                                    Status.getText().clear();
                                }else{
                                    Toast.makeText(MainActivity.this,"Type in status a number from 1 to 4",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }
}