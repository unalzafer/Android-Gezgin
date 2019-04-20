package com.a.gezginapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etMail=(EditText)findViewById(R.id.etMail);
        final EditText etPassword=(EditText)findViewById(R.id.etPassword);
        Button btRegister=(Button) findViewById(R.id.btRegister);

        mAuth = FirebaseAuth.getInstance();



        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mail ve şifre değerlerini aldım.
                String mail=etMail.getText().toString();
                String pass=etPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(mail,pass)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                     Log.d("firebase", "iŞLEM BAŞARILI"+task.toString());
                                } else {
                                   Toast.makeText(RegisterActivity.this,"Başarısız"+task.toString()
                                           ,Toast.LENGTH_SHORT).show();
                                }

                            }
                        });



            }
        });
    }
}
