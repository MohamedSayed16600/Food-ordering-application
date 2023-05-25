package com.example.wagba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wagba.adapters.User;
import com.example.wagba.adapters.UserDao;
import com.example.wagba.adapters.UserDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends Fragment {

    TextView fullNameTextBox;
    TextView emailTextBox;
    TextView phoneTextBox;
    TextView addresTextBox;

    // Firebase
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    private static UserDatabase userDatabase;
    private static UserDao userDao;


    public profile() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        userDatabase = Room.databaseBuilder(
                getActivity().getApplicationContext(),
                UserDatabase.class,
                "user_database"
        ).allowMainThreadQueries().build();
        userDao = userDatabase.userDao();

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


        fullNameTextBox = view.findViewById(R.id.nameprof);
        emailTextBox = view.findViewById(R.id.mailprof);
        phoneTextBox = view.findViewById(R.id.numberprof);




        User user = userDao.getUser(firebaseUser.getEmail());

        fullNameTextBox.setText(("Name: "+user.getFullName()));
        emailTextBox.setText(("E-mail: "+user.getEmail()));
        phoneTextBox.setText(("Phone number: "+user.getPhoneNumber()));
     //   addresTextBox.setText(("Address: "+user.getAddress()));

        return view;
    }
}