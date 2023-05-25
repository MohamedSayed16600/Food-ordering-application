package com.example.wagba.ui.Favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.models.FavoritesModel;
import com.example.wagba.trackorder;
import com.example.wagba.ui.Home.Myviewholder3orderhis;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.example.wagba.R;
//import com.example.wagba.databinding.FragmentFavoritesBinding;


public class Orderhis extends Fragment {

    RecyclerView orderhistoryrecy;

    FirebaseRecyclerOptions<FavoritesModel> OrderOptions;
    FirebaseRecyclerAdapter<FavoritesModel, Myviewholder3orderhis> OrderAdapter;
    DatabaseReference OrderRef;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_order_his, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        orderhistoryrecy =root.findViewById(R.id.orderhistoryrecy);
        orderhistoryrecy.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        orderhistoryrecy.setNestedScrollingEnabled(false);


        OrderRef= FirebaseDatabase.getInstance().getReference().child("OrderHistory").child(user.getUid());
        orderhistoryrecy.setAdapter(OrderAdapter);

        LoadOrderData();



        return root;
    }

    private void LoadOrderData() {
        OrderOptions=new FirebaseRecyclerOptions.Builder<FavoritesModel>().setQuery(OrderRef,FavoritesModel.class).build();
        OrderAdapter=new FirebaseRecyclerAdapter<FavoritesModel, Myviewholder3orderhis>(OrderOptions) {
            @Override
            protected void onBindViewHolder(@NonNull Myviewholder3orderhis holder, int position, @NonNull FavoritesModel model) {

                holder.orderID.setText(model.getOrderID());
                holder.status.setText(model.getStatus());
                holder.gate.setText(model.getGate());
                holder.date.setText(model.getDate());
                holder.time.setText(model.getTime()+",");
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle=new Bundle();
                        bundle.putString("ID",getRef(position).getKey());
                        Fragment track_order =new trackorder();
                        track_order.setArguments(bundle);
                        FragmentTransaction transaction= getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainerView,track_order);
                        transaction.addToBackStack(null);
                        transaction.setReorderingAllowed(true);
                        transaction.commit();
                    }
                });

            }

            @NonNull
            @Override
            public Myviewholder3orderhis onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.orderhistory_item,parent,false);
                return new Myviewholder3orderhis(v);

            }
        };
        OrderAdapter.startListening();
        orderhistoryrecy.setAdapter(OrderAdapter);
    }
}