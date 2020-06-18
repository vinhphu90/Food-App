package com.examplevinhphutvp.foodapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Upload_Recipe extends AppCompatActivity {
    
    ImageView recipeImage;
    Uri uri;
    EditText txtname,txtdescription,txtprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__recipe);
        
        recipeImage = (ImageView)findViewById(R.id.foodImage);
        txtname = (EditText)findViewById(R.id.edtName);
        txtdescription = (EditText)findViewById(R.id.edtDescription);
        txtprice = (EditText)findViewById(R.id.edtPrice);
    }

    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if(resultCode == RESULT_OK) {
            uri = data.getData();
            recipeImage.setImageURI(uri);
        }else
            Toast.makeText(this, "Bạn chưa chọn hình ảnh", Toast.LENGTH_SHORT).show();
    }

    public void uploadImage () {


    }
}