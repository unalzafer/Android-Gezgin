package com.a.gezginapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a.gezginapp.Model.StoryModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;

public class AddStoryActivity extends AppCompatActivity {

    EditText etTitle;
    Button btSend;
    ImageView ivStory;
    private static  final int GALLERY_INTENT=61;
    Uri filePath;
    private StorageReference mStorageRef;
    DatabaseReference myRef;
    String uniqId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        ivStory=(ImageView)findViewById(R.id.ivStory);
        etTitle=(EditText)findViewById(R.id.etTitle);
        btSend=(Button)findViewById(R.id.btSend);



        ivStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileImage();
            }
        });



        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etTitle.getText().toString().equals("")&& filePath!=null) {

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                     myRef = database.getReference(getString(R.string.travel))
                            .child(getString(R.string.user))
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(getString(R.string.story));

                     uniqId = myRef.push().getKey();
                    mStorageRef = FirebaseStorage.getInstance()
                            .getReference(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(uniqId);
                    uploadFile();

                /*myRef.child(getString(R.string.name)).setValue("Ünal Zafer");
                myRef.child(getString(R.string.photo)).setValue("Photo Url");
                myRef.child(getString(R.string.title)).setValue(etTitle.getText().toString());
                myRef.child(getString(R.string.like)).setValue("3");
                myRef.child(getString(R.string.date)).setValue(Calendar.getInstance().getTime().toString());

*/
                    AlertDialog.Builder alert = new AlertDialog.Builder(AddStoryActivity.this);
                    alert.setTitle("İşlem Sonucu")
                            .setMessage("Notunuz Kaydedildi");
                    alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();

                    // Toast.makeText(getApplicationContext(),"Ekleme Başarılı",Toast.LENGTH_SHORT).show();
                    //finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Açıklama ve Fotoğraf Boş Girilemez",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivStory.setImageBitmap(bitmap);
                //uploadFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void showFileImage() {
        //Galeriye ulaşmak için
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,GALLERY_INTENT);
    }

    private void uploadFile() {



        if (filePath != null) {



            mStorageRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    getUrlStorage();
                }
            });


        }
        else {

        }
    }
    public void getUrlStorage(){
        mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                StoryModel storyModel = new StoryModel(
                        "Ünal Zafer",
                        uri.toString(),
                        Calendar.getInstance().getTime().toString(),
                        "2",
                        etTitle.getText().toString());
                myRef.child(uniqId).setValue(storyModel);
            }
        });
    }

}
