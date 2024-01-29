package com.example.mysignupapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysignupapp.adapters.NavCategoryAdapter;
import com.example.mysignupapp.models.NavCategoryModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    FirebaseFirestore db;
    RecyclerView navCategoryRec;

    // Nav Category Items
    NavCategoryAdapter navCategoryAdapter;
    List<NavCategoryModel> navCategoryModelList;
    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_category, container, false);
        db = FirebaseFirestore.getInstance();

        navCategoryRec = root.findViewById(R.id.navCatRec);

        // Popular Items
        navCategoryRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        navCategoryModelList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter(getActivity(), navCategoryModelList);
        navCategoryRec.setAdapter(navCategoryAdapter);

        db.collection("NavCategory").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for (QueryDocumentSnapshot document : task.getResult()){

                    NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                    navCategoryModelList.add(navCategoryModel);
                    navCategoryAdapter.notifyDataSetChanged();
                }
            } else {

                Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

            }
        });




        return root;
    }
}