package com.example.helloword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
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

        final List<String> nameList = new ArrayList<String>();
        nameList.add("Activity");
        nameList.add("Service");
        nameList.add("BroadCast");
        nameList.add("contentProvider");
        nameList.add("UIControl");
        nameList.add("Fragment");

        //加载自定义数据
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < nameList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String name = nameList.get(i);
            map.put("title", name);
            map.put("info", name + "学习记录");
            map.put("img", R.drawable.header);
            list.add(map);
        }
        MyArrayAdapter adapter = new MyArrayAdapter(this, R.layout.item, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map result = list.get(position);
                switch (position)
                {
                    case 0:
                    {
                        goToNextActivity(result.get("title").toString());
                        break;
                    }
                    case 1:
                    {
                        goToNextActivity(result.get("title").toString());
                        break;
                    }
                    case 2:
                    {
                        sendBroadcast(result.get("title").toString());
                        break;
                    }
                    case 3:
                    {
                        goToControlAC(result);
                        break;
                    }
                    case 4:
                    {
                        goToControlAC(result);
                        break;
                    }
                    default:
                        break;
                }
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
        unregisterReceiver(receiver);
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

    public void goToControlAC(Map map) {
        Intent intent = new Intent(this, UIControlActivity.class);
        Intent intent1 = intent.putExtra(EXTRA_MESSAGE, map.get("title").toString());
        startActivity(intent);
    }
}