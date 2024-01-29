package com.example.mysignupapp;


import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if(itemId == R.id.navhome) {
                loadFragment(new HomeFragment(), false);

            }else if(itemId == R.id.navcat) {
                loadFragment(new CategoryFragment(),false);


            }else if(itemId == R.id.navcart) {
                loadFragment(new CartFragment(), false);

            }else if(itemId == R.id.navProduct) {
                loadFragment(new AddToCartFragment(), false);

            }else { // nav profile

                loadFragment(new ProfileFragment(), false);

                 }

            return true;
        });

        loadFragment(new HomeFragment(), true);

    }

    private void  loadFragment(Fragment fragment, boolean isappinitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isappinitialized) {

            fragmentTransaction.add(R.id.frameLayout, fragment);

            } else{
                fragmentTransaction.replace(R.id.frameLayout, fragment);
            }
        fragmentTransaction.commit();
        }
    }
