package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
/*
import com.example.wagba.adapters.Word;
import com.example.wagba.adapters.WordRoomDatabase;
import com.example.wagba.adapters.WordViewModel;*/
import com.example.wagba.adapters.User;
import com.example.wagba.adapters.UserDao;
import com.example.wagba.adapters.UserDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class register extends AppCompatActivity {



    Intent myintent2;
    Button btn2;
    EditText editTextEmail, editTextPassword;
    FirebaseAuth Mauth;
    EditText numbertext,addresstext;
    EditText usernametext;
    FirebaseUser firebaseUser;
    static UserDatabase userDatabase;
    private static UserDao userDao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Mauth=FirebaseAuth.getInstance();

        editTextEmail=findViewById(R.id.emailreg);
        editTextPassword=findViewById(R.id.passwordreg);

        numbertext=findViewById(R.id.phonenum);

        usernametext=findViewById(R.id.edit_word);


        userDatabase = Room.databaseBuilder(
                getApplicationContext(),
                UserDatabase.class,
                "user_database"
        ).allowMainThreadQueries().build();
        userDao = userDatabase.userDao();

        final Button button = findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {



                String p= String.valueOf(numbertext.getText());
                String c=String.valueOf(usernametext.getText());

                String email,password;
                email =String.valueOf(editTextEmail.getText());
                password =String.valueOf(editTextPassword.getText());

                Mauth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(register.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();

                                    User user = new User(email.toLowerCase(Locale.ROOT), c, p);
                                    userDao.insert(user);

                                    myintent2=new Intent(getApplicationContext(),login.class);
                                    startActivity(myintent2);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(register.this, "Failed to register.",
                                            Toast.LENGTH_SHORT).show();
                                    myintent2=new Intent(getApplicationContext(),login.class);
                                    startActivity(myintent2);
                                    finish();

                                }
                            }
                        });
            }
        });
    }

    }


