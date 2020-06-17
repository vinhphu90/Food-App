package com.examplevinhphutvp.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView foodDescription;
    ImageView foodImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        foodDescription = (TextView)findViewById(R.id.txtDescription2);
        foodImage =  (ImageView) findViewById(R.id.ImgView2);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            foodDescription.setText(bundle.getString("Description"));
            foodImage.setImageResource(bundle.getInt("Image"));
        }
    }
}