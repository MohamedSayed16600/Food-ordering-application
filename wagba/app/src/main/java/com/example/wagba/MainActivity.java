package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wagba.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    AppBarConfiguration mAppBarConfiguration;
    ActivityMainBinding binding;
    Button btnlogout;
    Intent intentlogout;
    //Menu item;
    FirebaseAuth auth;
    FirebaseUser user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);





        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        btnlogout=findViewById(R.id.logoutbutton);

        if(user==null){
            intentlogout=new Intent(getApplicationContext(),login.class);
            startActivity(intentlogout);
            finish();
        }
       /* else{
            intentlogout=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intentlogout);
            finish();
        }*/

        findViewById(R.id.menuView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);

            }
        });
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        NavController navController= Navigation.findNavController(this,R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(navigationView,navController);
        Log.d("oufa", "onCreate: ");
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                intentlogout=new Intent(getApplicationContext(),login.class);
                startActivity(intentlogout);
                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}