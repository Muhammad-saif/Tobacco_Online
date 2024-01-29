package com.example.mysignupapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseUser user;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);


        auth = FirebaseAuth.getInstance();

        Button button = root.findViewById(R.id.logOut);
        textView = root.findViewById(R.id.user_mail);
        user = auth.getCurrentUser();

        if (user != null){
            textView.setText(user.getEmail());
        } else {
            Toast.makeText(getActivity(), "You need to logIn to view your profile !", Toast.LENGTH_SHORT).show();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToNewActivity();
            }
        });


        return root;
    }


    private void moveToNewActivity () {

        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getActivity(), "LogOut Successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        requireActivity().overridePendingTransition(0, 0);

    }
}