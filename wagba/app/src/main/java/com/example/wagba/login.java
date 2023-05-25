package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    Intent myintent,myintentlog;
    Button btn1,btn2;
    TextInputEditText editTextEmail, editTextPassword;
    FirebaseAuth Mauth;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mauth=FirebaseAuth.getInstance();

        editTextEmail=findViewById(R.id.emaillog);
        editTextPassword=findViewById(R.id.passwordlog);
        btn1=findViewById(R.id.registerButton);
        btn2=findViewById(R.id.loginButton);



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                String email,password;
                email =String.valueOf(editTextEmail.getText());
                password =String.valueOf(editTextPassword.getText());

                Mauth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
                                    myintentlog=new Intent(login.this,MainActivity.class);
                                    startActivity(myintentlog);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(login.this, "Wrong format.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                myintent=new Intent(getApplicationContext(),register.class);
                startActivity(myintent);


            }
        });
    }
}