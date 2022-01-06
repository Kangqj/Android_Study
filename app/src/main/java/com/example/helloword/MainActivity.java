package com.example.helloword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.MyArrayAdapter;
import Broadcast.NormalReceiver;

public class MainActivity extends AppCompatActivity {
    public static  final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final String LARGE_BASE_URL = "https://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/large/flying_in_the_light.jpg";
    private static final String NORMAL_ACTION = "com.example.normal.receiver";
    NormalReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        /*
        //只加载纯文字数据
        final List<String> adapterData = new ArrayList<String>();
        //存放要显示的数据
        for (int i=0; i<20; i++) {
            adapterData.add("ListItem" + i);
        }
        //创建Adapter对象并设置适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, adapterData);
        //将listview绑定到adapter
        listView.setAdapter(adapter);
        */

        //加载自定义数据
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "第" + i + "title");
            map.put("info", "第" + i + "info");
            map.put("img", R.drawable.header);
            list.add(map);
        }
        MyArrayAdapter adapter = new MyArrayAdapter(this, R.layout.item, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map result = list.get(position);
                System.out.println("你点击了" + result.get("title") + result.get("info"));
                goToNextActivity(result.get("title").toString());

                sendBroadcast(result.get("title").toString());
            }
        });
    }

    //注册通知
    @Override
    protected void onResume() {
        super.onResume();
        receiver = new NormalReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.normal.receiver");
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause(){
        super.onPause();
//        unregisterReceiver(receiver);
    }

    public void sendBroadcast(String msg) {
        Intent intent = new Intent(NORMAL_ACTION);
        Intent intent1 = intent.putExtra("Msg", msg);
        sendBroadcast(intent);
    }

    public void goToNextActivity(String message) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        Intent intent1 = intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        Intent intent1 = intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void loadImage(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Picasso.with(image.getContext()).load(LARGE_BASE_URL).into(image);
    }
}