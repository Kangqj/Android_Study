package com.example.helloword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Adapter.Fruit;
import Adapter.TestRecyclerAdapter;

public class UIControlActivity extends AppCompatActivity {

    private static final String LARGE_BASE_URL = "https://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/large/flying_in_the_light.jpg";

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uicontrol);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        setTitle(message);

        initFruits();
        //RecyclerView
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView_one);
        //设置固定大小
        recyclerView.setHasFixedSize(true);
        //创建线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //加载数据
        TestRecyclerAdapter adapter = new TestRecyclerAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits() {
        for (int i=0; i < 10; i++) {
            int index = i+1;
            Fruit fruit = new Fruit(index + "Apple", R.drawable.apple);
            fruitList.add(fruit);
        }
    }

    public void loadImage(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView2);
        Picasso.with(image.getContext()).load(LARGE_BASE_URL).into(image);
    }
}