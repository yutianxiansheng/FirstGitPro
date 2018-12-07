package com.example.yikeyuanjianhuanglei.myhotdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView textView=findViewById(R.id.main_tv);
        textView.setText("发布补丁，热修复是否成功了呢");
        textView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }
}
