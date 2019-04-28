package com.a.gezginapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a.gezginapp.Model.StoryModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeFragment extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_home, container, false);

        final TextView tvTitle=(TextView)view.findViewById(R.id.tvTitle);

       /* //firebase veritabanı yazma
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("gyk");
*/
        //myRef.setValue("Selam");

        DatabaseReference databaseReference=FirebaseDatabase.getInstance()
                .getReference(getString(R.string.travel))
                .child(getString(R.string.user));
                //.push()
                //.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                //.child(getString(R.string.story));

        // firebase veritabanı okuma
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    StoryModel model=ds.getValue(StoryModel.class);
                    tvTitle.setText(model.getTitle());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return view;
    }

}
