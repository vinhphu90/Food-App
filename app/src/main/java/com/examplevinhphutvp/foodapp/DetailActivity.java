package com.examplevinhphutvp.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {
    TextView foodDescription,foodName,foodPrice,foodAddress,foodPhone;
    ImageView foodImage;
    String key ="";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        foodName = (TextView)findViewById(R.id.txtfoodname2);
        foodPrice = (TextView)findViewById(R.id.txtPrice2) ;
        foodDescription = (TextView)findViewById(R.id.txtDescription2);
        foodImage =  (ImageView) findViewById(R.id.ImgView2);
        foodAddress = (TextView)findViewById(R.id.txtAddress2);
        foodPhone = (TextView)findViewById(R.id.txtPhone2);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            foodDescription.setText(bundle.getString("Description"));
            key = bundle .getString("KeyValue");
            imageUrl = bundle.getString("Image");
            foodName.setText(bundle.getString("foodName"));
            foodPrice.setText(bundle.getString("price"));
            foodAddress.setText(bundle.getString("Address"));
            foodPhone.setText(bundle.getString("Phone"));
            //foodImage.setImageResource(bundle.getInt("Image"));

            Glide.with(this)
                    .load(bundle.getString("Image"))
                    .into( foodImage);
        }
    }

    public void btnDeleteFood(View view) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Food");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                reference.child(key).removeValue();
                Toast.makeText(DetailActivity.this, "Food Delete", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }
        });
    }

    public void btnUpdateFood(View view) {

        startActivity(new Intent(getApplicationContext(), UpdateFoodActivity.class)
                .putExtra("foodNameKey",foodName.getText().toString())
                .putExtra("descriptionKey",foodDescription.getText().toString())
                .putExtra("priceKey",foodPrice.getText().toString())
                .putExtra("addressKey",foodAddress.getText().toString())
                .putExtra("phoneKey",foodPhone.getText().toString())
                .putExtra("oldimageUrl",imageUrl)
                .putExtra("key",key));
    }
}