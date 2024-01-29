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

import com.example.mysignupapp.adapters.CategoryAdapter;
import com.example.mysignupapp.adapters.PopularAdapters;
import com.example.mysignupapp.adapters.RecommendedAdapter;
import com.example.mysignupapp.models.CategoryModel;
import com.example.mysignupapp.models.PopularModel;
import com.example.mysignupapp.models.RecommendedModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    FirebaseFirestore db;
    RecyclerView popularRec, categoryRec, recommendedRec;

    // Popular Items
    PopularAdapters popularAdapters;
    List<PopularModel> popularModelList;

    // Category Items
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    // Recommended Items
    RecommendedAdapter recommendedAdapter;
    List<RecommendedModel> recommendedModelList;

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         View root = inflater.inflate(R.layout.fragment_home, container, false);
         db = FirebaseFirestore.getInstance();


         popularRec = root.findViewById(R.id.popRec);
         categoryRec = root.findViewById(R.id.catRec);
         recommendedRec = root.findViewById(R.id.rec_rec);

        // Popular Items
         popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
         popularModelList = new ArrayList<>();
         popularAdapters = new PopularAdapters(getActivity(), popularModelList);
         popularRec.setAdapter(popularAdapters);

        db.collection("PopularProducts").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for (QueryDocumentSnapshot document : task.getResult()){

                    PopularModel popularModel = document.toObject(PopularModel.class);
                    popularModelList.add(popularModel);
                    popularAdapters.notifyDataSetChanged();
                }
            } else {

                Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

            }
        });



        // category Items
        categoryRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(), categoryModelList);
        categoryRec.setAdapter(categoryAdapter);

        db.collection("HomeCategory").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for (QueryDocumentSnapshot document : task.getResult()){

                    CategoryModel categoryModel = document.toObject(CategoryModel.class);
                    categoryModelList.add(categoryModel);
                    categoryAdapter.notifyDataSetChanged();
                }
            } else {

                Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

            }
        });


        // Recommended Items
        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(), recommendedModelList);
        recommendedRec.setAdapter(recommendedAdapter);

        db.collection("Recommended").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for (QueryDocumentSnapshot document : task.getResult()){

                    RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                    recommendedModelList.add(recommendedModel);
                    recommendedAdapter.notifyDataSetChanged();
                }
            } else {

                Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

            }
        });


         return  root;
    }

}