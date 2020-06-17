package com.examplevinhphutvp.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<FoodData> myFoodList;
    FoodData mFoodData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        myFoodList = new ArrayList<>();

        mFoodData = new FoodData("Bánh Khọt","Bánh khọt là loại bánh Việt Nam (chính xác là loại bánh đặc trưng của miền nam Việt Nam) làm từ bột gạo hoặc bột sắn, có nhân tôm, được nướng và ăn kèm","50K",R.drawable.hinh_mon_banh_khot);
        myFoodList.add(mFoodData);
        mFoodData = new FoodData("Bánh Mì Thịt","Bánh mì thịt hay bánh mì kẹp thịt, là cho thêm thịt heo quay hay thịt nguội xiên quê ,dưa chua , và một số gia vị vào rất nhanh và tienj lợi thường dung để ăn sáng ","20K",R.drawable.hinh_mon_banh_my);
        myFoodList.add(mFoodData);
        mFoodData = new FoodData("Bí Ngòi Chiên","Bí ngòi chiên xù là loại món ăn rất được ưa chuộng ở Việt Nam ,rất ngon và dễ ăn , được ăn với rau sống và chấm nước mắm chua cay ","50K",R.drawable.hinh_mon_bi_ngoi_chien_xu);
        myFoodList.add(mFoodData);
        mFoodData = new FoodData("Bún mắm","Bún mắm là một trong số các món ăn đặc sản của miền tây Nam bộ Việt Nam. Bún mắm có nguồn gốc từ Campuchia, được nấu từ mắm bù hốc. Khi sang đến Việt","30K",R.drawable.hinh_mon_bun_mam);
        myFoodList.add(mFoodData);
        mFoodData = new FoodData("Bún Rêu Cua","Bún riêu cua là một món ăn truyền thống Việt Nam được biết đến rộng rãi trong nước và quốc tế . Món ăn này gồm bún (bún rối hoặc bún lá) và 'riêu cua'","25K",R.drawable.hinh_mon_bun_rieu);
        myFoodList.add(mFoodData);
        mFoodData = new FoodData("Món Mực","món ăn nhẹ. Mực rim me bao gồm những nguyên liệu chính như: Mực khô nướng Me chín chua ngọt Dầu thực vật Đường, muối, ớt, tỏi, sả Mực khô: làm sạch, cán","150K",R.drawable.hinh_mon_muc);
        myFoodList.add(mFoodData);

        MyAdapter myAdapter = new MyAdapter(MainActivity.this,myFoodList);
        mRecyclerView.setAdapter(myAdapter);

    }
}