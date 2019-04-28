package com.a.gezginapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a.gezginapp.Adapter.StoryAdapter;
import com.a.gezginapp.Model.StoryModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    View view;
    ArrayList<StoryModel> list;
    RecyclerView recyclerView;
    StoryAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        list=new ArrayList<>();
       /* //firebase veritabanı yazma
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("gyk");
*/
        //myRef.setValue("Selam");

        DatabaseReference databaseReference=FirebaseDatabase.getInstance()
                .getReference(getString(R.string.travel))
                .child(getString(R.string.user));

        // firebase veritabanı okuma
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    for (DataSnapshot data:ds.child("Story").getChildren()) {
                        StoryModel model = data.getValue(StoryModel.class);
                        list.add(model);
                    }
                }

                adapter=new StoryAdapter(list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return view;
    }

}
