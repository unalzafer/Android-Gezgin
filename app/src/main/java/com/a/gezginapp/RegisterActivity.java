package com.a.gezginapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
                final String mail=etMail.getText().toString();
                final String pass=etPassword.getText().toString();

                if (!mail.contains("@")){
                    Toast.makeText(getApplicationContext(), "Lütfen geçerli bir email adresi giriniz.", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(), "Email alanı boş bırakılamaz",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(), "Parola alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
                }
                else if(pass.length()<6){
                    Toast.makeText(getApplicationContext(),"Şifre 6 haneden küçük olamaz",Toast.LENGTH_LONG).show();
                }
                else {

                    mAuth.createUserWithEmailAndPassword(mail, pass)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("firebase", "iŞLEM BAŞARILI" + task.toString());
                                        Toast.makeText(RegisterActivity.this, "Üyelik Gerçekleşti" + task.toString()
                                                , Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(RegisterActivity.this,Login.class);
                                        i.putExtra("email",mail);
                                        i.putExtra("password",pass);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Başarısız" + task.toString()
                                                , Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });


                }
            }
        });
    }
}
