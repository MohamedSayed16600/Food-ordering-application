package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import com.example.wagba.adapters.Cartadaptor;
import com.example.wagba.adapters.Cartadaptor;
import com.example.wagba.models.Cartmodel;
import com.example.wagba.models.HomeHorModel;
import com.example.wagba.ui.Home.MyViewHolder;
import com.example.wagba.ui.Home.Myviewholdercart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MyCartFragment extends Fragment {

    List<Cartmodel> list;

    RecyclerView recyclerViewCart;

    FirebaseRecyclerAdapter<Cartmodel, Myviewholdercart> adapterCart;
    FirebaseRecyclerOptions<Cartmodel> optionsCart;
    DatabaseReference cartreference;

    String idcart="";
    Button makeorder;
    Intent makeorderto;
    TextView totalprice;
    Boolean flag=true;
    int price=0;

    String quannum;
    public MyCartFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseUser user;
        user= FirebaseAuth.getInstance().getCurrentUser();

        cartreference= FirebaseDatabase.getInstance().getReference().child("Cart").child(user.getUid());

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_cart, container, false);
        totalprice=view.findViewById(R.id.pricetotal);
        recyclerViewCart=view.findViewById(R.id.cart_rec);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        list=new ArrayList<>();

        LoadDataCart();

        makeorder=view.findViewById(R.id.make_order);
        makeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeorderto=new Intent(getContext(),checkout.class);
                startActivity(makeorderto);

            }
        });


        return view;


    }

    private void LoadDataCart() {

        optionsCart=new FirebaseRecyclerOptions.Builder<Cartmodel>().setQuery(cartreference,Cartmodel.class).build();
        adapterCart=new FirebaseRecyclerAdapter<Cartmodel, Myviewholdercart>(optionsCart) {
            @Override
            protected void onBindViewHolder(@NonNull Myviewholdercart holder, int position, @NonNull Cartmodel model) {

                holder.nameCart.setText(model.getNameCart());
                holder.pricecart.setText(model.getPriceCart());
                Picasso.get().load(model.getImageCart()).into(holder.imagecart);



                holder.addquan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        quannum=holder.quan.getText().toString();
                        int price_1=Integer.valueOf(holder.pricecart.getText().toString())/Integer.valueOf(holder.quan.getText().toString());

                        holder.quan.setText(String.valueOf(Integer.valueOf(quannum)+1));
                        holder.pricecart.setText(String.valueOf(price_1*Integer.valueOf(holder.quan.getText().toString())));
                    }
                });

                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cartreference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot msnap: snapshot.child(getRef(holder.getAdapterPosition()).getKey()).getChildren()){
                                    msnap.getRef().removeValue();
                                    notifyItemRemoved(holder.getAdapterPosition());
                                }
                                return;
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
                holder.minquan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        quannum=holder.quan.getText().toString();
                        Log.d("manga", holder.quan.getText().toString());
                        int delete=Integer.valueOf(holder.quan.getText().toString())-1;
                        if(delete==0){
                            cartreference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot msnap: snapshot.child(getRef(holder.getAdapterPosition()).getKey()).getChildren()){
                                        msnap.getRef().removeValue();
                                        notifyItemRemoved(holder.getAdapterPosition());
                                    }
                                    return;
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }else{
                        //mohamedsayed@gmail.com
                        //123456
                        int price_1=Integer.valueOf(holder.pricecart.getText().toString())/Integer.valueOf(holder.quan.getText().toString());
                        holder.quan.setText(String.valueOf(Integer.valueOf(quannum)-1));

                        Log.d("manga", String.valueOf(price_1));
                        holder.pricecart.setText(String.valueOf(price_1*Integer.valueOf(holder.quan.getText().toString())));
                    }}
                });


            }

            @NonNull
            @Override
            public Myviewholdercart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View Cartview = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false);
                return new Myviewholdercart(Cartview);
            }
        };
        adapterCart.startListening();
        recyclerViewCart.setAdapter(adapterCart);
    }
}