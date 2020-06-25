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
    TextView foodDescription;
    ImageView foodImage;
    String key ="";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        foodDescription = (TextView)findViewById(R.id.txtDescription2);
        foodImage =  (ImageView) findViewById(R.id.ImgView2);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            foodDescription.setText(bundle.getString("Description"));
            key = bundle .getString("KeyValue");
            imageUrl = bundle.getString("Image");
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
}