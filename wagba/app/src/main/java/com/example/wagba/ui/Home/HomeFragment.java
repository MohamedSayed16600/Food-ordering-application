package com.example.wagba.ui.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;
//import com.example.wagba.adapters.HomeHorAdapter;
import com.example.wagba.adapters.HomeVerAdapter;
import com.example.wagba.models.Cartmodel;
import com.example.wagba.models.HomeHorModel;
import com.example.wagba.models.HomeVerModel;
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


public class HomeFragment extends Fragment  {

    RecyclerView homeHorizontalRec,homeVerticalRec;

    FirebaseRecyclerAdapter <HomeHorModel,MyViewHolder>adapter;
    FirebaseRecyclerOptions<HomeHorModel>options;

    FirebaseRecyclerOptions<HomeVerModel>options2;
    FirebaseRecyclerAdapter<HomeVerModel,MyViewHolder2>adapter2;

    DatabaseReference dataref;
    DatabaseReference dataref2;
    DatabaseReference dataref22;
    DatabaseReference cartRef;


    String id="";

    Cartmodel mycart;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeHorizontalRec =root.findViewById(R.id.home_hor_rec);


        dataref=FirebaseDatabase.getInstance().getReference().child("Rest");
        dataref2=FirebaseDatabase.getInstance().getReference().child("Dishes");
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        homeHorizontalRec.setNestedScrollingEnabled(false);


        homeVerticalRec=root.findViewById(R.id.home_ver_rec);


        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        homeVerticalRec.setNestedScrollingEnabled(false);

        homeHorizontalRec.setAdapter(adapter);
        homeVerticalRec.setAdapter(adapter2);
        LoadData();


        return root;
    }

    private void LoadData2(String id) {
        options2 = new FirebaseRecyclerOptions.Builder<HomeVerModel>().setQuery(dataref2.orderByChild("id").equalTo(id), HomeVerModel.class).build();
        adapter2=new FirebaseRecyclerAdapter<HomeVerModel, MyViewHolder2>(options2) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder2 holder, int position, @NonNull HomeVerModel model) {
                holder.textView1.setText((model.getName()));
                holder.textView2.setText((model.getPrice()));

                holder.textView3.setText((model.getTiming()));
                Picasso.get().load(model.getImageurl()).into(holder.imageView2);
                holder.addcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dataref22=FirebaseDatabase.getInstance().getReference().child("Dishes").child(getRef(position).getKey());

                        Log.d("hhh", getRef(position).getKey());
                        mycart = new Cartmodel();
                        mycart.setNameCart(String.valueOf(holder.textView1.getText()));
                        mycart.setPriceCart(String.valueOf(holder.textView2.getText()));

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        cartRef = FirebaseDatabase.getInstance().getReference().child("Cart");



                        cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if(snapshot.child(user.getUid()).child(dataref22.getKey()).exists()){

                                    Toast.makeText(getContext(),"Item Already Exists in cart",Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    dataref22.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            HomeVerModel dish=snapshot.getValue(HomeVerModel.class);
                                            mycart.setImageCart(dish.getImageurl());

                                            cartRef.child(user.getUid()).child(dataref22.getKey()).setValue(mycart);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    Toast.makeText(getContext(),"Item Added successfully",Toast.LENGTH_SHORT).show();
                                }
                                }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }});
            }

            @NonNull
            @Override
            public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View H =LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item,parent,false);
                return new MyViewHolder2(H);

            }
        };
        adapter2.startListening();
        homeVerticalRec.setAdapter(adapter2);
    }

    private void LoadData() {
        options=new FirebaseRecyclerOptions.Builder<HomeHorModel>().setQuery(dataref,HomeHorModel.class).build();

        adapter=new FirebaseRecyclerAdapter<HomeHorModel, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull HomeHorModel model) {

                holder.textView.setText(model.getName());
                Picasso.get().load(model.getImageurl()).into(holder.imageView);

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        id=adapter.getRef(holder.getAdapterPosition()).getKey();
                        LoadData2(id);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item,parent,false);


                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        homeHorizontalRec.setAdapter(adapter);
    }



}