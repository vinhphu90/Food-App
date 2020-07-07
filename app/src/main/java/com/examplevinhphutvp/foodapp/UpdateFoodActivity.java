package com.examplevinhphutvp.foodapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateFoodActivity extends AppCompatActivity {

    ImageView FoodImage;
    Uri uri;
    EditText txtname,txtdescription,txtprice,txtaddress,txtphone;
    String imageUrl;
    String key,oldImageUrl;
    String foodname,foodDescription,foodPrice,foodAddress,foodPhone;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    private String IDuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food);

        FoodImage = (ImageView)findViewById(R.id.foodImage);
        txtname = (EditText)findViewById(R.id.edtName);
        txtdescription = (EditText)findViewById(R.id.edtDescription);
        txtprice = (EditText)findViewById(R.id.edtPrice);
        txtaddress = (EditText)findViewById(R.id.edtAddress);
        txtphone = (EditText)findViewById(R.id.edtPhone);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        IDuser = user.getUid();
        Bundle mbundle = getIntent().getExtras();
        if (mbundle!=null) {

            Glide.with(UpdateFoodActivity.this)
                    .load(mbundle.getString("oldimageUrl"))
                    .into(FoodImage);
            txtname.setText(mbundle.getString("foodNameKey"));
            txtdescription.setText(mbundle.getString("descriptionKey"));
            txtprice.setText(mbundle.getString("priceKey"));
            key = mbundle.getString("key");
            txtaddress .setText(mbundle.getString("addressKey"));
            txtphone.setText(mbundle.getString("phoneKey"));
            oldImageUrl = mbundle.getString("oldimageUrl");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference(IDuser+"_Food").child(key);

       
    }

    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null) {
            switch (requestCode){
                case   2 :
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    FoodImage.setImageBitmap(bitmap);

                    break;
                case  1:
                    uri = data.getData();
                    FoodImage.setImageURI(uri);
                    break;
            }


        }else
            Toast.makeText(this, "Bạn chưa chọn hình ảnh", Toast.LENGTH_SHORT).show();

    }

    public void btnUpdateFood(View view) {
         foodname = txtname.getText().toString().trim();
         foodDescription = txtdescription.getText().toString().trim();
         foodAddress = txtaddress.getText().toString().trim();
         foodPhone = txtphone.getText().toString().trim();
         foodPrice = txtprice.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" đang tải lên ....");
        progressDialog.show();
        storageReference = FirebaseStorage.getInstance()
                .getReference().child(IDuser+"_FoodImage")
                .child(uri.getLastPathSegment());
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
    }


    public void uploadRecipe() {
        FoodData foodData = new FoodData(
                foodname,
                foodDescription,
                foodPrice,
                imageUrl,
                foodAddress,
                foodPhone

                );
        databaseReference.setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                StorageReference storageReferenceNew = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageUrl);
                storageReferenceNew.delete();
                Toast.makeText(UpdateFoodActivity.this, "Đã cập nhật dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void btn_camera(View view) {
//        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(cameraIntent, 2);
//    }
//
//@Override
//public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//    if (requestCode == REQUEST_CODE_CAMERA) {
//        if (grantResults[0] == PERMISSION_GRANTED) {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(intent, REQUEST_CODE_CAMERA);
//        }
//    }
//    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//}

}
