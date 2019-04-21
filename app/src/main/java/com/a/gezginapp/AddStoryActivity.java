package com.a.gezginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddStoryActivity extends AppCompatActivity {

    EditText etTitle;
    Button btSend;
    ImageView ivStory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        ivStory=(ImageView)findViewById(R.id.ivStory);
        etTitle=(EditText)findViewById(R.id.etTitle);
        btSend=(Button)findViewById(R.id.btSend);



        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(getString(R.string.travel))
                        .child(getString(R.string.user))
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child(getString(R.string.story))
                        .child("1");

                myRef.child(getString(R.string.name)).setValue("Ünal Zafer");
                myRef.child(getString(R.string.photo)).setValue("Photo Url");
                myRef.child(getString(R.string.title)).setValue(etTitle.getText().toString());
                myRef.child(getString(R.string.like)).setValue("3");
                myRef.child(getString(R.string.date)).setValue(Calendar.getInstance().getTime().toString());

                Toast.makeText(getApplicationContext(),"Ekleme Başarılı",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
