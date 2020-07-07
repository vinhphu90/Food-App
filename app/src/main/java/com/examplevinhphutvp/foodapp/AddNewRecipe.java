package com.examplevinhphutvp.foodapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class AddNewRecipe extends AppCompatActivity {
    private String IDUser;
    ImageView FoodImage;
    Uri uri;
    EditText txtname,txtdescription,txtprice,txtaddress,txtphone;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__recipe);

        FoodImage = (ImageView)findViewById(R.id.foodImage);
        txtname = (EditText)findViewById(R.id.edtName);
        txtdescription = (EditText)findViewById(R.id.edtDescription);
        txtprice = (EditText)findViewById(R.id.edtPrice);
        txtaddress = (EditText)findViewById(R.id.edtAddress);
        txtphone = (EditText)findViewById(R.id.edtPhone);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        IDUser = user.getUid();
    }

    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            uri = data.getData();
            FoodImage.setImageURI(uri);
        }else
            Toast.makeText(this, "Bạn chưa chọn hình ảnh", Toast.LENGTH_SHORT).show();
    }


    public void uploadImage () {
        if(uri != null) {
            StorageReference storageReference = FirebaseStorage.getInstance()
                    .getReference().child(IDUser + "_FoodImage")
                    .child(uri.getLastPathSegment());

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(" đang tải lên ....");
            progressDialog.show();
            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete()) ;
                    Uri urlImage = uriTask.getResult();
                    imageUrl = urlImage.toString();
                    uploadRecipe();
                    progressDialog.dismiss();


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                }
            });
        }else {
            Toast.makeText(this, "Bạn chưa nhập hình ảnh",Toast.LENGTH_LONG).show();
        }

    }

    public void btnUploadRecipe(View view) {

        uploadImage();
    }
    public void uploadRecipe() {
        FoodData foodData = new FoodData(
                txtname.getText().toString(),
                txtdescription.getText().toString(),
                txtprice.getText().toString(),
                imageUrl,
                txtaddress.getText().toString(),
                txtphone.getText().toString()



        );
        
        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference(IDUser+"_Food")
                .child(myCurrentDateTime).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddNewRecipe.this, "hoàn tất", Toast.LENGTH_SHORT).show();
                    finish();
                }
                
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNewRecipe.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}