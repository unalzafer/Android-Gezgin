package com.a.gezginapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etMail=(EditText)findViewById(R.id.etMail);
        final EditText etPassword=(EditText)findViewById(R.id.etPassword);
        Button btLogin=(Button) findViewById(R.id.btLogin);
        Button btRegister=(Button) findViewById(R.id.btRegister);

        mAuth =FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            etMail.setText(bundle.getString("email"));
            etPassword.setText(bundle.getString("password"));
        }

        // eğer firebase user değeri var ise otomatik giriş yap anasayfaya gönder
        if (!(user == null))
        {
            intent();
        }
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etMail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!email.contains("@")){
                    Toast.makeText(getApplicationContext(), "Lütfen geçerli bir email adresi giriniz.", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Email alanı boş bırakılamaz",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Parola alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Burada signin işlemi yapılacak
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                intent();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Bir hataoluştu.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivity(new Intent(Login.this,RegisterActivity.class));
            }
        });
    }

    private void intent(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
